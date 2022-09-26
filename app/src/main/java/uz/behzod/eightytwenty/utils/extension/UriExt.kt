package uz.behzod.eightytwenty.utils.extension

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

@SuppressLint("Range")
fun Uri.getFileName(context: Context): String {
    var fileName: String?
    val cursor = context.contentResolver.query(this, null, null, null)

    cursor?.moveToFirst()
    fileName = cursor?.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    cursor?.close()

    return fileName ?: ""
}