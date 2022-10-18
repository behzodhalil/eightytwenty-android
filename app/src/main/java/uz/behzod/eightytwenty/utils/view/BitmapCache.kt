package uz.behzod.eightytwenty.utils.view

import android.graphics.Bitmap
import android.util.LruCache


private const val SIZE = 1024

// Create the object to working with memory cache
object BitmapCache {

    private const val SIZE = 1024

    private var memoryCache: LruCache<String, Bitmap>

    init {
        // Define the maximum memory of the JVM
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

        val cacheSize = maxMemory / 4

        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return value?.byteCount?.div(1024) ?: 0
            }
        }
    }

    // This function is used to add a bitmap to the memory cache
    fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            memoryCache.put(key, bitmap)
        }
    }

    // This function is used to get bitmap/image from the cache
    fun getBitmapFromMemoryCache(key: String): Bitmap? {
        return memoryCache.get(key)
    }

    // This function is used to clear the memory cache
    fun clearMemoryCache() {
        return memoryCache.evictAll()
    }

}