package com.example.jubging.jubging

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.jubging.R
import net.daum.mf.map.api.MapReverseGeoCoder

class myDialogFragment(private val listener: OnClickDialogListener): DialogFragment() {

    interface OnClickDialogListener {
        fun onClickPositive(address: String)
        fun onClickNegative()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("줍깅을 종료할까요?")
                .setPositiveButton("예") { dialog, id ->
                    var startAddress = "None"
                    val sPoint = MyUtils.pointList[0]
                    val reverseGeoCoder =
                        MapReverseGeoCoder("52687ee17a411663d296e4faf8ac59b3", sPoint,
                            object : MapReverseGeoCoder.ReverseGeoCodingResultListener {
                                override fun onReverseGeoCoderFoundAddress(
                                    mapReverseGeocoder: MapReverseGeoCoder?,
                                    address: String?
                                ) {
                                    if (address != null) startAddress = address
                                    listener.onClickPositive(startAddress)
                                }
                                override fun onReverseGeoCoderFailedToFindAddress(p0: MapReverseGeoCoder?) {
                                    listener.onClickNegative()
                                }
                            }, activity
                        )

                    reverseGeoCoder.startFindingAddress()
                }
                .setNegativeButton("아니요") { dialog, id ->
                    listener.onClickNegative()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}