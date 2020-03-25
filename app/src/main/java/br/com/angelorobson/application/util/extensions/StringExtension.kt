package br.com.angelorobson.application.util.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.math.BigInteger

fun String.extractNumbers(): BigInteger {
    return this.replace(Regex("[^0-9]"), "").toBigInteger()
}

fun String?.getFileName(): String? {
    return this?.substring(this.lastIndexOf(".") + 1)
}

fun String?.getFileExntesion(): String? {
    return this?.substring(this.lastIndexOf(".") + 1)
}

fun String?.decodeFile(): Bitmap {
    return BitmapFactory.decodeFile(this)
}

fun String.convertBase64ToBitmap(): Bitmap {
    val imagesBytes = Base64.decode(this, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imagesBytes, 0, imagesBytes.size)
}

