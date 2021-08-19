package com.example.jubging.webapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class ImageUtils {

    companion object {
        fun getImageContentUri(context: Context, absPath: String): Uri? {

            val cursor: Cursor? = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Images.Media._ID),
                MediaStore.Images.Media.DATA + "=? ",
                arrayOf(absPath),
                null
            )

            if (cursor != null && cursor.moveToFirst()) {
                val id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID))
                return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI , id.toString())

            } else if (absPath.isNotEmpty()) {
                val values: ContentValues = ContentValues()
                values.put(MediaStore.Images.Media.DATA, absPath)
                return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

            } else {
                return null;
            }
        }

    }
}