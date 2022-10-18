package uz.behzod.eightytwenty.utils.view

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import pl.droidsonroids.gif.GifDrawable
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.helper.BitmapHelper
import uz.behzod.eightytwenty.utils.manager.ImageStorageManager
import java.util.concurrent.Executors

private val executor = Executors.newSingleThreadExecutor()
private val handler = Handler(Looper.getMainLooper())

fun ImageView.bindBitmap(uri: Uri?, width: Int, height: Int) {
    uri?.let { source ->
        val ivKey = source.toString()
        printDebug { "Image key is $ivKey" }
        var bitmapCache = BitmapCache.getBitmapFromMemoryCache(ivKey)
        var gifDrawable: GifDrawable? = null
        val mimeType = this.context.contentResolver.getType(source)

        printDebug { "Bitmap1 is $bitmapCache" }
        if (bitmapCache != null) {
            this.setImageBitmap(bitmapCache)
        }
        if (bitmapCache == null) {
            printDebug { "If is started when bitmap is null" }
            printDebug { "mime type is $mimeType" }
            if (mimeType == "image/gif") {
                gifDrawable = GifDrawable(this.context.contentResolver, source)
            } else {

                bitmapCache = BitmapHelper.getBitmapFromUri(this.context,uri,width,height)?.also {
                    printDebug { "Also is started" }
                    printDebug { "Also is bitmap is $it" }
                    BitmapCache.addBitmapToMemoryCache(ivKey,it)
                }
            }
        }

        printDebug { "Bitmap 4 is $bitmapCache" }

        Handler(Looper.getMainLooper()).post{
            if (bitmapCache != null && mimeType != "image/gif") {
                this.setImageBitmap(bitmapCache)
                printDebug { "Bitmap is not null $bitmapCache" }
            }
            if (gifDrawable != null && mimeType == "image/gif") {
                this.setImageDrawable(gifDrawable)
                printDebug { "Bitmap2 is not null $bitmapCache" }
            }
        }

        }


    }


object ViewBindingAdapter {


    @BindingAdapter("source", "width", "height", requireAll = true)
    fun bindImage(iv: ImageView, uri: Uri?, width: Int, height: Int) {
        uri?.let { source ->
            val imageKey = source.toString()
            var bitmapCache = BitmapCache.getBitmapFromMemoryCache(imageKey)
            var gifDrawable: GifDrawable? = null
            val mimeType = iv.context.contentResolver.getType(source)

            if (bitmapCache != null) {
                iv.setImageBitmap(bitmapCache)
            } else {
                executor.execute {
                    if (mimeType == "image/gif") {
                        gifDrawable = GifDrawable(iv.context.contentResolver, source)
                    } else {
                        bitmapCache =
                            BitmapHelper.getBitmapFromUri(iv.context, source, width, height)?.also {
                                BitmapCache.addBitmapToMemoryCache(imageKey, it)
                            }
                    }

                    Handler().post{
                        if (bitmapCache != null && mimeType != "image/gif") {
                            iv.setImageBitmap(bitmapCache)
                        }
                        if (gifDrawable != null && mimeType == "image/gif") {
                            iv.setImageDrawable(gifDrawable)
                        }
                    }
                }
            }


        }
    }
}