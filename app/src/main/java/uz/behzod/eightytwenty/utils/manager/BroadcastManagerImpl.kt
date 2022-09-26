package uz.behzod.eightytwenty.utils.manager

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import javax.inject.Inject

class BroadcastManagerImpl @Inject constructor(
    private val context: Context
): BroadcastManager {

    override fun transferToLocal(status: String, data: String?) {
       TODO()
    }
}