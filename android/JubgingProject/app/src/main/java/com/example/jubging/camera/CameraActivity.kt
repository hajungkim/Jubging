package com.example.jubging.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.hardware.camera2.*
import android.os.*
import android.util.Log
import android.util.Size
import android.view.Gravity
import android.view.Surface
import android.view.TextureView
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.jubging.R
import com.example.jubging.camera.ImageClassifier.Companion.create
import com.example.jubging.jubging.JubgingActivity
import com.example.jubging.webapp.MainActivity
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class CameraActivity : AppCompatActivity(), View.OnClickListener{

    private val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    private lateinit var mCameraId: String
    private lateinit var imageDimension: Size
    private var cameraDevice: CameraDevice? = null
    private lateinit var texture: SurfaceTexture
    private lateinit var textureView: AutoFitTextureView
    private lateinit var btn: Button
    private lateinit var captureRequestBuilder: CaptureRequest.Builder
    private var captureSession: CameraCaptureSession? = null
    private var isCertificated = false

    private var backgroundThread: HandlerThread? = null
    private var backgroundHandler: Handler? = null

    private val surfaceTextureListener = object : TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureSizeChanged(texture: SurfaceTexture, width: Int, height: Int) {
        }
        override fun onSurfaceTextureUpdated(texture: SurfaceTexture) = Unit
        override fun onSurfaceTextureDestroyed(texture: SurfaceTexture) = true
        override fun onSurfaceTextureAvailable(texture: SurfaceTexture, width: Int, height: Int) {
            openCamera(width, height)
        }
    }
    private val stateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera
            createCameraPreviewSession()
        }

        override fun onDisconnected(camera: CameraDevice) {
            cameraDevice!!.close()
        }

        override fun onError(camera: CameraDevice, error: Int) {
            cameraDevice!!.close()
            cameraDevice = null
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        textureView = findViewById(R.id.texture)
        btn = findViewById(R.id.btn_check)
        btn.setOnClickListener(this)

        startBackgroundThread();
    }

    override fun onResume() {
        super.onResume()

        startBackgroundThread()

        if (textureView.isAvailable) {
            openCamera(textureView.width, textureView.height)
        } else {
            textureView.surfaceTextureListener = surfaceTextureListener
        }
    }

    private fun openCamera(width: Int, height: Int) {

        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, CAMERA_PERMISSION, 99)
            return
        }

        initTensorFlowAndLoadModel()

        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            for (cameraId in manager.cameraIdList) {

                val characteristics = manager.getCameraCharacteristics(cameraId)

                val cameraDirection = characteristics.get(CameraCharacteristics.LENS_FACING)
                if (cameraDirection == null || cameraDirection == CameraCharacteristics.LENS_FACING_FRONT) {
                    continue
                }
                val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)

                imageDimension = map!!.getOutputSizes<SurfaceTexture>(SurfaceTexture::class.java).filter {
                    it.height == it.width
                }[0]

                manager.openCamera(cameraId, stateCallback, backgroundHandler)

                mCameraId = cameraId
                return
            }
        } catch (e: Exception) {
            val intent = Intent(this@CameraActivity, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "사용할 수 있는 카메라가 없습니다.", Toast.LENGTH_SHORT).show()
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
                openCamera(textureView.height, textureView.width)
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun createCameraPreviewSession() {
        try {

            texture = textureView.surfaceTexture!!

            texture.setDefaultBufferSize(imageDimension.width, imageDimension.height)

            val surface = Surface(texture)

            captureRequestBuilder = cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
            captureRequestBuilder.addTarget(surface)

            cameraDevice?.createCaptureSession(listOf(surface),
                object : CameraCaptureSession.StateCallback() {
                    override fun onConfigureFailed(session: CameraCaptureSession) {

                    }

                    override fun onConfigured(session: CameraCaptureSession) {
                        if(cameraDevice == null) {
                            return
                        }
                        captureSession = session
                        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)

                        try {
                            captureSession?.setRepeatingRequest(captureRequestBuilder.build(), null, backgroundHandler)
                        } catch (e: CameraAccessException) {
                            e.printStackTrace()
                        }
                    }

                }, null)

        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
    private var classifier: ImageClassifier? = null
    private val lock = Any()
    private var runClassifier = false
    private val HANDLE_THREAD_NAME = "CameraBackground"

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val MODEL_PATH = "model.tflite"
    private val LABEL_PATH = "labels.txt";
    private fun initTensorFlowAndLoadModel() {
        executor.execute {
            try {
                classifier = create(assets, MODEL_PATH, LABEL_PATH, 224, true)
            } catch (e: java.lang.Exception) {
                throw RuntimeException("Error initializing TensorFlow!", e)
            }
        }
    }
    private fun classifyFrame() {
        if (classifier == null || cameraDevice == null) {
            return
        }

        val bitmap = textureView.getBitmap(224, 224)
        val results: List<Classifier.Recognition?>? = classifier!!.recognizeImage(bitmap)
        if (!isCertificated && results != null) {
            for (result in results) {
                if (result != null && result.title == "1 봉투" && result.confidence != null) {
                    val percent = (result.confidence!! *100).toInt()
                    if (percent >= 30) {
                        isCertificated = true
                        btn.text = "인증 완료"
                        btn.background = getDrawable(R.drawable.btn_certificated)
                        btn.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                }
            }
        }
        bitmap!!.recycle()
    }

    private val periodicClassify: Runnable = object : Runnable {
        override fun run() {
            synchronized(lock) {
                if (runClassifier) {
                    classifyFrame()
                }
            }
            backgroundHandler!!.post(this)
        }
    }

    private fun startBackgroundThread() {
        backgroundThread = HandlerThread(HANDLE_THREAD_NAME);
        backgroundThread!!.start();
        backgroundHandler = Handler(backgroundThread!!.looper);
        synchronized (lock) {
            runClassifier = true;
        }
        backgroundHandler!!.post(periodicClassify);
    }


    private fun stopBackgroundThread() {
        backgroundThread!!.quitSafely()
        try {
            backgroundThread!!.join()
            backgroundThread = null
            backgroundHandler = null
            synchronized(lock) { runClassifier = false }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun closeCamera() {
        if (null != cameraDevice) {
            cameraDevice!!.close()
            cameraDevice = null
        }
    }

    override fun onPause() {
        super.onPause()
        stopBackgroundThread()
        closeCamera()
        executor.execute { classifier!!.close() }

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_check -> {
                if (isCertificated) {
                    val intent = Intent(this, JubgingActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("isJubgingOn", false)
        setResult(RESULT_OK, intent)
        finish()
    }
}

