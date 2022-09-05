package uz.behzod.eightytwenty.utils.providers

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface StringProvider {
    fun getString(@StringRes id: Int, vararg args: Any): String
}


class StringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : StringProvider {

    override fun getString(@StringRes id: Int, vararg args: Any): String {
        return context.getString(id,*args)
    }

}