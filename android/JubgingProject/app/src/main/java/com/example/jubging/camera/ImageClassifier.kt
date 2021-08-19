package com.example.jubging.camera

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.util.Log
import org.tensorflow.lite.Interpreter
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*
import kotlin.experimental.and

class ImageClassifier : Classifier {
    private var interpreter: Interpreter? = null
    private var inputSize = 0
    private var labelList: List<String>? = null
    private var quant = false

     override fun recognizeImage(bitmap: Bitmap?): List<Classifier.Recognition?>? {
        val byteBuffer = convertBitmapToByteBuffer(bitmap!!)
        return if (quant) {
            val result = Array(1) {ByteArray(labelList!!.size)}
            interpreter?.run(byteBuffer, result)
            getSortedResultByte(result)
        } else {
            val result = Array(1) {FloatArray(labelList!!.size) }
            interpreter?.run(byteBuffer, result)
            getSortedResultFloat(result)
        }
    }

    override fun close() {
        interpreter?.close()
        interpreter = null
    }

    @Throws(IOException::class)
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    @Throws(IOException::class)
    private fun loadLabelList(assetManager: AssetManager, labelPath: String): List<String> {
        val labelList: MutableList<String> = ArrayList()
        val reader = BufferedReader(InputStreamReader(assetManager.open(labelPath)))
        var line: String? = null
        while (true) {
            line = reader.readLine()
            if (line==null) break
            labelList.add(line)
        }
        reader.close()
        return labelList
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer: ByteBuffer = if (quant) {
            ByteBuffer.allocateDirect(BATCH_SIZE * inputSize * inputSize * PIXEL_SIZE)
        } else {
            ByteBuffer.allocateDirect(4 * BATCH_SIZE * inputSize * inputSize * PIXEL_SIZE)
        }
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(inputSize * inputSize)
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        var pixel = 0
        for (i in 0 until inputSize) {
            for (j in 0 until inputSize) {
                val `val` = intValues[pixel++]
                if (quant) {
                    byteBuffer.put((`val` shr 16 and 0xFF).toByte())
                    byteBuffer.put((`val` shr 8 and 0xFF).toByte())
                    byteBuffer.put((`val` and 0xFF).toByte())
                } else {
                    byteBuffer.putFloat(((`val` shr 16 and 0xFF) - IMAGE_MEAN) / IMAGE_STD)
                    byteBuffer.putFloat(((`val` shr 8 and 0xFF) - IMAGE_MEAN) / IMAGE_STD)
                    byteBuffer.putFloat(((`val` and 0xFF) - IMAGE_MEAN) / IMAGE_STD)
                }
            }
        }
        return byteBuffer
    }

    @SuppressLint("DefaultLocale")
    private fun getSortedResultByte(labelProbArray: Array<ByteArray>): List<Classifier.Recognition> {
        val pq: PriorityQueue<Classifier.Recognition> = PriorityQueue(
            MAX_RESULTS,
            kotlin.Comparator<Classifier.Recognition> { o1, o2 ->
                (o1.confidence!!).compareTo(o2.confidence!!)
            })

        for (i in labelList!!.indices) {
            val confidence:Float = (labelProbArray[0][i] and 255.toByte()).toFloat() / 255.0f
            Log.d("확률", "$i $confidence")
            if (confidence >= THRESHOLD) {
                pq.add(
                    Classifier.Recognition(
                        "" + i,
                        if (labelList!!.size > i) labelList!![i] else "unknown",
                        confidence, quant
                    )
                )
            }
        }
        val recognitions: ArrayList<Classifier.Recognition> = ArrayList<Classifier.Recognition>()
        val recognitionsSize = Math.min(pq.size, MAX_RESULTS)
        for (i in 0 until recognitionsSize) {
            recognitions.add(pq.poll())
        }
        return recognitions
    }

    @SuppressLint("DefaultLocale")
    private fun getSortedResultFloat(labelProbArray: Array<FloatArray>): List<Classifier.Recognition> {
        val pq: PriorityQueue<Classifier.Recognition> = PriorityQueue(
            MAX_RESULTS,
            kotlin.Comparator<Classifier.Recognition> { o1, o2 ->
                (o1.confidence!!).compareTo(o2.confidence!!)
            })
        for (i in labelList!!.indices) {
            val confidence = labelProbArray[0][i]
            if (confidence > THRESHOLD) {
                pq.add(
                    Classifier.Recognition(
                        "" + i,
                        if (labelList!!.size > i) labelList!![i] else "unknown",
                        confidence, quant
                    )
                )
            }
        }
        val recognitions: ArrayList<Classifier.Recognition> = ArrayList<Classifier.Recognition>()
        val recognitionsSize = Math.min(pq.size, MAX_RESULTS)
        for (i in 0 until recognitionsSize) {
            recognitions.add(pq.poll())
        }
        return recognitions
    }

    companion object {
        private const val MAX_RESULTS = 3
        private const val BATCH_SIZE = 1
        private const val PIXEL_SIZE = 3
        private const val THRESHOLD = 0.001f
        private const val IMAGE_MEAN = 128
        private const val IMAGE_STD = 128.0f

        @Throws(IOException::class)
        fun create(
            assetManager: AssetManager,
            modelPath: String,
            labelPath: String,
            inputSize: Int,
            quant: Boolean
        ): ImageClassifier? {
            val classifier = ImageClassifier()
            classifier.interpreter = Interpreter(classifier.loadModelFile(assetManager, modelPath), Interpreter.Options())
            classifier.labelList = classifier.loadLabelList(assetManager, labelPath)
            classifier.inputSize = inputSize
            classifier.quant = quant
            return classifier
        }
    }
}
