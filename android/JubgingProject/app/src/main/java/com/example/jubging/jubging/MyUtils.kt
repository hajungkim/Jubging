package com.example.jubging.jubging

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.preference.PreferenceManager
import com.example.jubging.R
import net.daum.mf.map.api.MapPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.*

class MyUtils {
    companion object {

        val pointList = ArrayList<MapPoint>()
        var totalDist = 0.0
        var time = "0:00:00"
        var startToggle = false
        var h = 0
        var m = 0
        var s = 0
        private var timerTask: Timer? = null
        @SuppressLint("StaticFieldLeak")
        var activity: JubgingActivity? = null

        fun startJubging() {


            startToggle = true
            timerTask = kotlin.concurrent.timer(period = 1000) {	// timer() 호출
                s++	// period=10, 0.01초마다 time를 1씩 증가

                if (s >= 60) {
                    s = 0
                    m ++
                    if (m >= 60) {
                        m = 0;
                        h ++
                    }
                }

                time = ""

                if (h == 0) time += "0:"
                else if(h<10) time += "$h:"
                else time += "$h:"

                if (m == 0) time += "00:"
                else if(m<10) time += "0$m:"
                else time += "$m:"

                if (s == 0) time += "00"
                else if(s<10) time += "0$s"
                else time += "$s"

                activity?.runOnUiThread {
                    activity?.updateTime(time)
                }
            }
        }

        fun stopJubging() {
            s = 0; m = 0; h = 0
            totalDist = 0.0
            startToggle = false
            timerTask?.cancel()
            timerTask = null
            pointList.clear()
        }
        private fun deg2rad(deg: Double): Double {
            return (deg * Math.PI / 180.0)
        }
        private fun rad2deg(rad: Double): Double {
            return (rad * 180 / Math.PI)
        }

        private fun getDistance(sLati:Double, sLongi:Double, eLati:Double, eLongi:Double): Double {

            var theta = sLongi - eLongi
            var dist = Math.sin(deg2rad(sLati)) * Math.sin(deg2rad(eLati)) + Math.cos(deg2rad(sLati)) * Math.cos(deg2rad(eLati)) * Math.cos(deg2rad(theta))

            dist = Math.acos(dist)
            dist = rad2deg(dist)
            dist *= 60 * 1.1515 * 1.609344

            return dist
        }

        fun pushNewPoint(location: Location?) {
            if (location != null) {
                if (pointList.size > 0) {
                    val lastLocation = pointList.last().mapPointGeoCoord
                    val dist = getDistance(lastLocation.latitude, lastLocation.longitude, location.latitude, location.longitude)

                    if (dist >= 0) {
                        val newPoint = MapPoint.mapPointWithGeoCoord(location.latitude, location.longitude)
                        pointList.add(newPoint)
                        totalDist += dist
                    }
                } else {
                    val newPoint = MapPoint.mapPointWithGeoCoord(location.latitude, location.longitude)
                    pointList.add(newPoint)
                }
            }
        }

        const val KEY_REQUESTING_LOCATION_UPDATES = "requesting_location_updates"

        fun requestingLocationUpdates(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
                KEY_REQUESTING_LOCATION_UPDATES, false)
        }

        fun setRequestingLocationUpdates(context: Context, requestingLocationUpdates: Boolean) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
                .apply()
        }
        fun getLocationText(location: Location?): String {
            return if(location == null) {
                "Unknown location"
            } else {
                if (pointList.size > 0)
                    "(${location.latitude}, ${pointList.last().mapPointGeoCoord.latitude}, ${pointList.size})"
                else "안뇽"
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun getLocationTitle(context: Context): String {
            return context.getString(
                R.string.location_updated,
                SimpleDateFormat("yyyy.MM.dd aaa hh:mm").format(Date()))
//            return context.getString(R.string.location_updated,
//                DateFormat.getDateTimeInstance().format(Date()))
        }
    }

}