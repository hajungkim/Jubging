package com.example.jubging.camera

import android.graphics.Bitmap

interface Classifier {
    class Recognition(
        /**
         * A unique identifier for what has been recognized. Specific to the class, not the instance of
         * the object.
         */
        val id: String?,
        /**
         * Display name for the recognition.
         */
        val title: String?,
        /**
         * A sortable score for how good the recognition is relative to others. Higher should be better.
         */
        val confidence: Float?,
        /**
         * Whether or not the model features quantized or float weights.
         */
        private val quant: Boolean
    ) {

        override fun toString(): String {

            var resultString = ""
            if (title != null) {
                resultString += "$title "
            }
            if (confidence != null) {
                resultString += String.format("(%.1f%%) ", confidence * 100.0f)
            }
            return resultString.trim { it <= ' ' }
        }
    }

    fun recognizeImage(bitmap: Bitmap?): List<Recognition?>?
    fun close()
}