package com.tsfapps.myapplication.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import java.io.ByteArrayOutputStream

object ImageConverter {
     fun convertImageViewToBitmap(v: ImageView): Bitmap? {
        return (v.drawable as BitmapDrawable).bitmap
    }
     fun bitMapToString(bitmap: Bitmap?): String {
        val bos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 30, bos)
        val b = bos.toByteArray()
        return java.util.Base64.getEncoder().encodeToString(b)
    }

}