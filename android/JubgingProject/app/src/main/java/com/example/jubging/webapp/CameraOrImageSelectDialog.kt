package com.example.jubging.webapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.jubging.R

class CameraOrImageSelectDialog(private val listener: OnClickSelectListener) : DialogFragment() {

    interface OnClickSelectListener {
        fun onClickCamera()
        fun onClickImage()
    }

    private val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    lateinit var mContext: Context
    lateinit var llSelectCamera: ImageView
    lateinit var llSelectImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_camera_image, container, false)
        llSelectCamera = view.findViewById(R.id.img_camera)
        llSelectImage = view.findViewById(R.id.img_gallery)
        return view
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog?.window?.setLayout(width, height)
            dialog?.window?.setGravity(Gravity.BOTTOM)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.setCancelable(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        llSelectCamera.setOnClickListener {

            val permission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), CAMERA_PERMISSION, 99)
            } else {
                listener.onClickCamera()
                dismiss()
            }
        }
        llSelectImage.setOnClickListener {
            listener.onClickImage()
            dismiss()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==99) {
            var checked = true
            for (grant in grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    checked = false
                    break
                }
            }
            if (checked) {
                listener.onClickCamera()
                dismiss()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}