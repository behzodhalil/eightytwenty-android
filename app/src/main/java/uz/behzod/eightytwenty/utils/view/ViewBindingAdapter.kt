package uz.behzod.eightytwenty.utils.view

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import pl.droidsonroids.gif.GifDrawable
import uz.behzod.eightytwenty.utils.helper.BitmapHelper
import java.util.concurrent.Executors

private val executor = Executors.newSingleThreadExecutor()
private val handler = Handler(Looper.getMainLooper())

fun ImageView.bindBitmap(uri: Uri?, width: Int, height: Int) {
    uri?.let { source ->
        val ivKey = source.toString()
        var bitmapCache = BitmapCache.getBitmapFromMemoryCache(ivKey)
        var gifDrawable: GifDrawable? = null
        val mimeType = this.context.contentResolver.getType(source)

        if (bitmapCache != null) {
            this.setImageBitmap(bitmapCache)
        } else {
            executor.execute {
                if (mimeType == "image/gif") {
                    gifDrawable = GifDrawable(this.context.contentResolver, source)
                } else {
                    bitmapCache =
                        BitmapHelper.getBitmapFromUri(
                            this.context, source,
                            width, height
                        )?.also {
                            BitmapCache.addBitmapToMemoryCache(ivKey, it)
                        }
                }
            }
        }
        handler.post {
            if (bitmapCache != null && mimeType != "image/gif") {
                this.setImageBitmap(bitmapCache)
            }
            if (gifDrawable != null && mimeType == "image/gif") {
                this.setImageDrawable(gifDrawable)
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

                    handler.post {
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