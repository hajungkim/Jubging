package com.example.jubging.jubging

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Camera
import android.graphics.Color
import android.graphics.Typeface
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.graphics.scaleMatrix
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.preference.PreferenceManager
import com.example.jubging.BuildConfig
import com.example.jubging.MyService
import com.example.jubging.R
import com.example.jubging.databinding.ActivityJubgingBinding
import com.example.jubging.jubging.MyUtils.Companion.activity
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import net.daum.mf.map.api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.*


class JubgingActivity : AppCompatActivity(),  SharedPreferences.OnSharedPreferenceChangeListener, View.OnClickListener{

    private val TAG = JubgingActivity::class.java.simpleName
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    private var myReceiver: JubgingActivity.MyReceiver? = null
    private var mService: MyService? = null
    private var mBound = false
    private var isTracking = true
    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mService = null
            mBound = false
        }
    }
    private lateinit var binding: ActivityJubgingBinding
    private lateinit var mapView: MapView
    private val currentMarker = MapPOIItem()
    private var point = MapPoint.mapPointWithGeoCoord(36.35111, 127.38500)
    private val polyline = MapPolyline()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJubgingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.time.typeface = Typeface.DEFAULT_BOLD
        binding.distance.text = String.format("%.2f", MyUtils.totalDist)
        binding.distance.typeface = Typeface.DEFAULT_BOLD
        binding.btnStart.text = if (!MyUtils.startToggle) "줍깅 시작" else "줍깅 종료"
        MyUtils.activity = this

        mapView = MapView(this)

        if (!checkPermissions()) requestPermissions()

        val mapViewContainer = findViewById<View>(R.id.map_view) as ViewGroup
        mapViewContainer.addView(mapView)

        if(MyUtils.pointList.size > 0) {
            point = MyUtils.pointList.last()
            val geo = point.mapPointGeoCoord
            searchKeyword("공원", geo.longitude.toString(), geo.latitude.toString(), 5000)
        }

        currentMarker.apply {
            itemName = "내 위치";
            tag = 1;
            mapPoint = point
            markerType = MapPOIItem.MarkerType.YellowPin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin

        }
        mapView.addPOIItem(currentMarker)
        mapView.setMapCenterPoint(point, false)
        polyline.lineColor = Color.BLUE
        polyline.addPoints(MyUtils.pointList.toTypedArray())
        mapView.addPolyline(polyline)

        binding.btnStart.setOnClickListener(this)
        binding.btnTracking.setOnClickListener(this)

        myReceiver = MyReceiver()
//        if (MyUtils.requestingLocationUpdates(this)) {
//            if (!checkPermissions()) {
//                requestPermissions()
//            }
//        }
    }

    fun updateTime(time: String) {
        binding.time.text = time
    }

    override fun onStart() {
        super.onStart()
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)

//        binding.startButton.setOnClickListener {
//            if (!checkPermissions()) {
//                requestPermissions()
//            } else {
//                mService!!.requestLocationUpdates()
//            }
//        }
//        binding.endButton.setOnClickListener {
//            mService!!.removeLocationUpdates()
//        }
//        setButtonsState(MyUtils.requestingLocationUpdates(this))

        bindService(
            Intent(this, MyService::class.java), mServiceConnection,
            BIND_AUTO_CREATE
        )
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            myReceiver!!,
            IntentFilter(MyService.ACTION_BROADCAST)
        )
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver!!)
        super.onPause()
    }

    override fun onStop() {
        if (mBound) {
            unbindService(mServiceConnection)
            mBound = false
        }
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
        super.onStop()
    }

    private fun checkPermissions(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.")
            Snackbar.make(
                findViewById(R.id.activity_main),
                R.string.permission_rationale,
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(R.string.ok) { // Request permission
                    ActivityCompat.requestPermissions(
                        this@JubgingActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_PERMISSIONS_REQUEST_CODE
                    )
                }
                .show()
        } else {
            Log.i(TAG, "Requesting permission")
            ActivityCompat.requestPermissions(
                this@JubgingActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isEmpty()) {
                Log.i(TAG, "User interaction was cancelled.")
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted.
                mService!!.requestLocationUpdates()
            } else {
//                Permission denied.
//                setButtonsState(false)
                Snackbar.make(
                    findViewById(R.id.activity_main),
                    R.string.permission_denied_explanation,
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction(R.string.settings) {
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts(
                            "package",
                            BuildConfig.APPLICATION_ID, null
                        )
                        intent.data = uri
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                    .show()
            }
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key.equals(MyUtils.KEY_REQUESTING_LOCATION_UPDATES)) {
//            setButtonsState(sharedPreferences!!.getBoolean(MyUtils.KEY_REQUESTING_LOCATION_UPDATES,
//                false))
        }
    }

//    private fun setButtonsState(requestingLocationUpdates: Boolean) {
//        if (requestingLocationUpdates) {
//            binding.startButton.isEnabled = false
//            binding.endButton.isEnabled = true
//        } else {
//            binding.startButton.isEnabled = true
//            binding.endButton.isEnabled = false
//        }
//    }

    private fun drawPolyLine() {
        val lastPoint = MyUtils.pointList.last()
        val geo = lastPoint.mapPointGeoCoord
        currentMarker.mapPoint = lastPoint
        if (isTracking) mapView.setMapCenterPoint(lastPoint, false)
        polyline.addPoint(lastPoint)
        mapView.addPolyline(polyline)

        searchKeyword("공원", geo.longitude.toString(), geo.latitude.toString(), 5000)
    }

    // 키워드 검색 함수
    private fun searchKeyword(keyword: String, x: String, y: String, radius: Int) {
        val retrofit = Retrofit.Builder()   // Retrofit 구성
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(KakaoAPI::class.java)   // 통신 인터페이스를 객체로 생성
        val call = api.getSearchKeyword("KakaoAK 0341cbbc65f256aeabdad32da27e1bbf", keyword, x, y, radius)   // 검색 조건 입력

        // API 서버에 요청
        call.enqueue(object: Callback<ResultSearchKeyword> {
            override fun onResponse(
                call: Call<ResultSearchKeyword>,
                response: Response<ResultSearchKeyword>
            ) {
                for ( place in response.body()!!.documents) {
                    val marker = MapPOIItem()
                    marker.apply {
                        itemName = place.place_name
                        tag = place.id.toInt()
                        mapPoint = MapPoint.mapPointWithGeoCoord(place.y.toDouble(), place.x.toDouble())
                        markerType = MapPOIItem.MarkerType.BluePin
                        selectedMarkerType = MapPOIItem.MarkerType.RedPin
                    }
                    mapView.addPOIItem(marker);
                }
            }

            override fun onFailure(call: Call<ResultSearchKeyword>, t: Throwable) {
                // 통신 실패
                Log.w("MainActivity", "통신 실패: ${t.message}")
            }
        })
    }

    inner class MyReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val location = intent!!.getParcelableExtra<Location>(MyService.EXTRA_LOCATION)
            if (location != null) {

                MyUtils.pushNewPoint(location)

                if (polyline.pointCount == 0 || MyUtils.pointList.last() != polyline.getPoint(polyline.pointCount-1)) {
                    drawPolyLine()
                    binding.distance.text = String.format("%.2f", MyUtils.totalDist)
                }
            }
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start -> {
                if (!MyUtils.startToggle) {
                    if (!checkPermissions()) {
                        requestPermissions()
                    } else {
                        mService!!.requestLocationUpdates()
                        binding.btnStart.text = "줍깅 종료"
                        MyUtils.startJubging()
                    }
                } else {
                    val dialog = myDialogFragment(object: myDialogFragment.OnClickDialogListener {
                        override fun onClickPositive(address: String) {

                            val jubgingInfo = address +"/"+binding.time.text+"/"+binding.distance.text

                            MyUtils.stopJubging()
                            mService!!.removeLocationUpdates()

                            val intent = Intent().apply {
                                putExtra("jubgingInfo", jubgingInfo)
                                putExtra("isJubgingOn", true)
                            }
                            activity!!.setResult(Activity.RESULT_OK, intent)
                            activity!!.finish()
                        }

                        override fun onClickNegative() {

                        }
                    })
                    dialog.show(supportFragmentManager, "myDialog")
                }
            }
            R.id.btn_tracking -> {
                if (isTracking) {
                    isTracking = false
                    binding.btnTracking.background = getDrawable(R.drawable.ic_baseline_gps_not_fixed_24)
                } else {
                    isTracking = true
                    binding.btnTracking.background = getDrawable(R.drawable.ic_baseline_gps_fixed_24)
                    if (MyUtils.pointList.isNotEmpty())
                        mapView.setMapCenterPoint(MyUtils.pointList.last(), false)
                }
            }
        }
    }

    override fun onBackPressed() {
        intent.putExtra("isJubgingOn", MyUtils.startToggle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}