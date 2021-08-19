package com.example.jubging

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.location.Location
import android.os.*
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.jubging.jubging.JubgingActivity
import com.example.jubging.jubging.MyUtils
import com.example.jubging.webapp.MainActivity
import com.google.android.gms.location.*

class MyService : Service() {
    private val EXTRA_STARTED_FROM_NOTIFICATION = "$PACKAGE_NAME.started_from_notification"
    private val TAG = MyService::class.simpleName
    private val CHANNEL_ID = "channel_01"

    private val mBinder: IBinder = LocalBinder()

    private val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 2000
    private val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2

    private val NOTIFICATION_ID = 12345678

    private lateinit var mNotificationManager: NotificationManager
    private var mChangingConfiguration = false
    private var mLocationRequest: LocationRequest? = null
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLocationCallback: LocationCallback? = null
    private var mServiceHandler: Handler? = null
    private var mLocation: Location? = null

    companion object {
        const val PACKAGE_NAME = "com.example.jubging"
        const val EXTRA_LOCATION = "$PACKAGE_NAME.location"
        const val ACTION_BROADCAST = "$PACKAGE_NAME.broadcast"
    }
    init {
    }

    override fun onCreate() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                onNewLocation(locationResult.lastLocation)
            }
        }
        createLocationRequest()
        getLastLocation()
        val handlerThread = HandlerThread(TAG)
        handlerThread.start()

        mServiceHandler = Handler(handlerThread.looper)
        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.app_name)
            val mChannel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT)
            mNotificationManager.createNotificationChannel(mChannel)
        }
        //super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service started")
        val startedFromNotification = intent!!.getBooleanExtra(EXTRA_STARTED_FROM_NOTIFICATION,
            false)

        if (startedFromNotification) {
            removeLocationUpdates()
            stopSelf()
        }
        return START_NOT_STICKY
        //super.onStart(intent, startId)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mChangingConfiguration = true
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "in onBind()")
        stopForeground(true)
        mChangingConfiguration = false
        return mBinder
    }

    override fun onRebind(intent: Intent?) {
        Log.i(TAG, "in onRebind()")
        stopForeground(true)
        mChangingConfiguration = false
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "Last client unbound from service")
        if(!mChangingConfiguration && MyUtils.requestingLocationUpdates(this)) {
            Log.i(TAG, "Start foreground service")
            startForeground(NOTIFICATION_ID, getNotification())
        }
        return true
    }

    override fun onDestroy() {
        mServiceHandler?.removeCallbacksAndMessages(null)
        //super.onDestroy()
    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates() {
        Log.i(TAG, "Requesting Location updates")
        MyUtils.setRequestingLocationUpdates(this, true)
        startService(Intent(applicationContext, MyService::class.java))
        try{
            mFusedLocationClient?.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
        }catch (unlikely: SecurityException) {
            MyUtils.setRequestingLocationUpdates(this, false)
            Log.e(TAG, "Lost location permission. Could not request updates. $unlikely")
        }
    }

    fun removeLocationUpdates() {
        Log.i(TAG, "Removing location updates")
        try {
            mFusedLocationClient?.removeLocationUpdates(mLocationCallback)
            MyUtils.setRequestingLocationUpdates(this, false)
            stopSelf()
        } catch (unlikely: SecurityException) {
            MyUtils.setRequestingLocationUpdates(this, true)
            Log.e(TAG, "Lost location permission. Could not remove updates. $unlikely")
        }
    }

    private fun getNotification(): Notification {

        val intent:Intent = Intent(this, MyService::class.java)
        MyUtils.pushNewPoint(mLocation)
        val text:CharSequence = "시간: "+MyUtils.time+", 거리: "+String.format("%.2f", MyUtils.totalDist)+" km"
        intent.putExtra(EXTRA_STARTED_FROM_NOTIFICATION, true)

//        val servicePendingIntent: PendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val activityPendingIntent = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), 0)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .addAction(R.drawable.common_google_signin_btn_icon_dark, getString(R.string.launch_activity), activityPendingIntent)
//            .addAction(R.drawable.common_google_signin_btn_icon_light, getString(R.string.remove_location_updates), servicePendingIntent)
            .setContentText(text)
            .setContentTitle(MyUtils.getLocationTitle(this))
            .setOngoing(true)
            .setPriority(Notification.PRIORITY_MAX)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker(text)
            .setWhen(System.currentTimeMillis())

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID)
        }
        return builder.build()
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        try{
            mFusedLocationClient?.lastLocation!!
                .addOnCompleteListener { task ->
                    if(task.isSuccessful && task.result != null) {
                        mLocation = task.result
                    } else {
                        Log.w(TAG, "Failed to get location.")
                    }
                }
        } catch (unlikely: SecurityException) {
            Log.e(TAG, "Lost location permission.$unlikely")
        }
    }

    fun onNewLocation(location: Location) {
        Log.i(TAG, "New location $location")
        mLocation = location

        val intent = Intent(ACTION_BROADCAST)
        intent.putExtra(EXTRA_LOCATION, location)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)

        if(serviceIsRunningInForeground(this)) {
            mNotificationManager.notify(NOTIFICATION_ID, getNotification())
        }
    }

    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()
            .setInterval(UPDATE_INTERVAL_IN_MILLISECONDS)
            .setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    }

    inner class LocalBinder: Binder() {
        fun getService(): MyService? {
            return this@MyService
        }
    }

    private fun serviceIsRunningInForeground(context: Context):Boolean {
        val manager = context.getSystemService(
            ACTIVITY_SERVICE) as ActivityManager

        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            Log.i(TAG, "Service is ${service.service.className}")
            if (javaClass.name == service.service.className) {
                if (service.foreground) {
                    return true
                }
            }
        }
        return false
    }
}
