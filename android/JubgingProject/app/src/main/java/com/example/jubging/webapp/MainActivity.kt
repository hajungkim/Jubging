package com.example.jubging.webapp

import android.app.Activity
import android.app.ActivityManager
import android.content.*
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.jubging.MyService
import com.example.jubging.camera.CameraActivity

import com.example.jubging.databinding.ActivityMainBinding
import com.example.jubging.jubging.JubgingActivity
import com.nguyenhoanglam.imagepicker.model.Config
import com.nguyenhoanglam.imagepicker.model.Image
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker
import java.io.*


class MainActivity : AppCompatActivity(), MyWebChromeClient.IImageHandler, MyWebChromeClient.GotoUrl{

    private lateinit var binding: ActivityMainBinding

    private val CAPTURE_CAMERA_RESULT = 3089
    private var filePathCallbackLollipop: ValueCallback<Array<Uri>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val wv = binding.webview
        wv.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
        }
        wv.apply {
            webViewClient = WebViewClient()
            webChromeClient = MyWebChromeClient(this@MainActivity)
            addJavascriptInterface(WebAppInterface(), "Android")
            loadUrl("https://i5b207.p.ssafy.io/home")
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webview.canGoBack()) {
            binding.webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    inner class WebAppInterface() {

        @JavascriptInterface
        fun startCameraActivity() {
            val intent = Intent(this@MainActivity, CameraActivity::class.java)
            startActivityForResult(intent, 800)
        }

        @JavascriptInterface
        fun startJubgingActivity() {
            val intent = Intent(this@MainActivity, JubgingActivity::class.java)
            startActivityForResult(intent, 800)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if(resultCode == Activity.RESULT_OK){
            Log.d("MDM", "In onActivityResult")

            when(requestCode){
                CAPTURE_CAMERA_RESULT -> {
                    onCaptureImageResult(intent)

                    filePathCallbackLollipop?.onReceiveValue(null)
                    filePathCallbackLollipop = null
                }
                Config.RC_PICK_IMAGES -> {

                    if (intent != null) {
                        val images: ArrayList<Image> = intent.getParcelableArrayListExtra(Config.EXTRA_IMAGES)!!

                        val data = Intent().apply {
                            data = ImageUtils.getImageContentUri(this@MainActivity, images[0].path)
                        }

                        uploadImageOnPage(resultCode, data)
                    }
                }
                800 -> {
                    if (intent != null) {
                        val isJubgingOn = intent.getBooleanExtra("isJubgingOn", false)
                        Log.d("줍깅 시작하고 뒤로갔을 때", isJubgingOn.toString())
                        binding.webview.loadUrl("javascript:onJubging(${isJubgingOn})")
                        if (intent.hasExtra("jubgingInfo")) {
                            val jubgingInfo = intent.getStringExtra("jubgingInfo")
                            if (jubgingInfo != null) {
                                val sendData = "javascript:finishJubging(\"${jubgingInfo}\")"
                                binding.webview.loadUrl(sendData)
                            }
                        }
                    }
                }
            }
        } else {
            filePathCallbackLollipop?.onReceiveValue(null)
            filePathCallbackLollipop = null
        }
    }


    override fun showDialog(callBack: ValueCallback<Array<Uri>>?) {
        filePathCallbackLollipop = callBack
        showSelectCameraOrImage()
    }

    /** * 이미지를 웹뷰로 리턴시켜준다. */

    override fun uploadImageOnPage(resultCode: Int, intent: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                filePathCallbackLollipop?.onReceiveValue( WebChromeClient.FileChooserParams.parseResult(Activity.RESULT_OK, intent) )
                filePathCallbackLollipop = null
            }
        }
    }


    /** * 카메라 / 갤러리 선택 팝업을 표시한다. */

    private fun showSelectCameraOrImage() {

        CameraOrImageSelectDialog(object: CameraOrImageSelectDialog.OnClickSelectListener {
            override fun onClickCamera() {
                cameraIntent()
            }
            override fun onClickImage() {
                galleryIntent()
            }
        }).show(supportFragmentManager, "CameraOrImageSelectDialog")
    }
    /** * 카메라 작동 */
    private fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAPTURE_CAMERA_RESULT)
    }

    /** * 이미지 선택 */

    private fun galleryIntent() {
        ImagePicker.with(this).run {
            setToolbarColor("#FFFFFF")
            setStatusBarColor("#FFFFFF")
            setToolbarTextColor("#000000")
            setToolbarIconColor("#000000")
            setProgressBarColor("#FFC300")
            setBackgroundColor("#FFFFFF")
            setCameraOnly(false)
            setMultipleMode(false)
            setFolderMode(true)
            setShowCamera(false)
            setFolderTitle("갤러리")
            setDoneTitle("완료")
//            setKeepScreenOn(true)
            setMultipleMode(false)
            start()
        }
    }
    /** * 카메라 작동후 전달된 인텐트를 받는다. */
    private fun onCaptureImageResult(data: Intent?) {
        if (data != null) {
            val thumbnail = data.extras!!.get("data") as Bitmap
            saveImage(thumbnail)
        }
    }
    /** * 비트맵을 로컬에 물리적 이미지 파일로 저장시킨다. */
    private fun saveImage(bitmap: Bitmap) {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        val imgFile = File(storageDir, "${System.currentTimeMillis()}.jpg")
        val fo: FileOutputStream
        try {
            imgFile.createNewFile()
            fo = FileOutputStream(imgFile)
            fo.write(bytes.toByteArray())
            fo.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        uploadImageOnPage(Activity.RESULT_OK, Intent().apply {
            data = imgFile.toUri()
        })
    }

    override fun goToUrl() {
        binding.webview.loadUrl("javascript:isJubging()")
    }
}



