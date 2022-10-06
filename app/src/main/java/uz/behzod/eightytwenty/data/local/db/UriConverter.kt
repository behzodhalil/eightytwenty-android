package uz.behzod.eightytwenty.data.local.db

import android.net.Uri
import androidx.room.TypeConverter

class UriConverter {
    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(str: String?): Uri? {
        return str?.let {
            Uri.parse(str)
        }
    }
}