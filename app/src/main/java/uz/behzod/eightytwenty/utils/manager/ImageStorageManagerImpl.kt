package uz.behzod.eightytwenty.utils.manager

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.helper.BitmapHelper
import java.io.File
import java.io.FileOutputStream

object ImageStorageManager {

    suspend fun saveImage(context: Context, uri: Uri, fileName: String): String = withContext(
        Dispatchers.IO
    ) {
        val directory = "${context.filesDir}/images"
        printDebug { "Directory is $directory and filename is $fileName" }
        printDebug { "Uri is $uri" }
        context.contentResolver.openInputStream(uri).use {
            FileOutputStream(File(directory, fileName)).use { fos ->
                val buffer = ByteArray(1024)
                var read = it?.read(buffer) ?: -1
                while (read != -1 ) {
                    fos.write(buffer, 0, read)
                    read = it?.read(buffer) ?: -1
                }
                fos.flush()
            }
        }
        printDebug { "Directory is $directory and filename is $fileName" }
        "$directory/$fileName"
    }


    fun getImageFromInternalStorage(
        context: Context,
        uri: Uri,
        width: Int,
        height: Int
    ): Bitmap? {
        return BitmapHelper.getBitmapFromUri(
            context, uri, width, height
        )
    }
}