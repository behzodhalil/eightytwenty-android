package uz.behzod.eightytwenty.utils.manager

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

interface ImageStoreManager {

    suspend fun saveImage(context: Context, uri: Uri, fileName: String): String
    fun getImageFromInternalStorage(context: Context, uri: Uri, width: Int, height: Int): Bitmap?
}