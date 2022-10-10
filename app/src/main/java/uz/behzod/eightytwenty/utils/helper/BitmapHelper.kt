package uz.behzod.eightytwenty.utils.helper

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.extension.printFailure
import java.io.File
import java.io.FileNotFoundException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object BitmapHelper {

    fun getBitmapFromUri(
        context: Context,
        uri: Uri,
        width: Int,
        height: Int
    ): Bitmap? {
        val bitmap: Bitmap?
        try {
            var inputStream = context.contentResolver.openInputStream(uri)
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()
            inputStream = context.contentResolver.openInputStream(uri)
            options.apply {
                inSampleSize = setupInSampleSize(options, width, height)
            }

            printDebug { "BitmapFactory.Options is ${options.inJustDecodeBounds}" }

            bitmap = BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()
            return bitmap
        } catch (e: FileNotFoundException) {
            printFailure { "File is not found" }
            return null
        }
    }

    fun saveImage(context: Context): Uri? {
        val fileName = buildString {
            append("IMG_")
            append(DateTimeFormatter.ofPattern("yyyyMMdd_HHss").format(LocalDateTime.now()))
            append("_${(0..9999).random()}.jpg")
        }

        val directory = "${Environment.DIRECTORY_PICTURES}/EightyTwenty"

        val imageUri: Uri? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, directory)
            }
            context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
        } else {
            val imageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val appImagesDirectory =
                File("${imageDirectory}/EightyTwenty")
            if (!appImagesDirectory.exists()) {
                appImagesDirectory.mkdir()
            }
            val imageFile = File(appImagesDirectory, "/$fileName")
            FileProvider.getUriForFile(context, "uz.behzod.eightytwenty", imageFile)
        }
        return imageUri
    }

    private fun setupInSampleSize(
        options: BitmapFactory.Options,
        width: Int, height: Int
    ): Int {

        var inSampleSize = 1

        val (_width: Int, _height: Int) = options.run {
            outWidth to outHeight
        }

        if (_height > height || _width > width) {
            val halfWidth: Int = _width / 2
            val halfHeight: Int = _height / 2

            while (halfHeight / inSampleSize >= height && halfWidth / inSampleSize >= width) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}