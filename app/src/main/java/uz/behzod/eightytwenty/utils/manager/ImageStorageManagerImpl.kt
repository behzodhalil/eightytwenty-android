package uz.behzod.eightytwenty.utils.manager

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.utils.helper.BitmapHelper
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageStorageManagerImpl @Inject constructor(
    private val dispatcher: IDispatcherProvider
): ImageStoreManager {

    override suspend fun saveImage(context: Context, uri: Uri, fileName: String): String {
        return withContext(dispatcher.io) {
            val directory = "${context.filesDir}/images"
            context.contentResolver.openInputStream(uri).use { istream ->
                FileOutputStream(File(directory,fileName)).use { fos ->
                    val buffer = ByteArray(size = 1024)
                    var read = istream?.read(buffer) ?: -1
                    while (read != -1) {
                        fos.write(buffer,0,read)
                        read = istream?.read(buffer) ?: -1
                    }
                    fos.flush()
                }
            }
            "$directory/$fileName"
        }

    }

    override fun getImageFromInternalStorage(
        context: Context,
        uri: Uri,
        width: Int,
        height: Int
    ): Bitmap? {
        return BitmapHelper.getBitmapFromUri(
            context,uri, width,height)
    }
}