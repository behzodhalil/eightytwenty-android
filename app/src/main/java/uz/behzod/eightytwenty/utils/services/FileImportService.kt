package uz.behzod.eightytwenty.utils.services

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import org.apache.commons.io.FileUtils
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_ACTION_IMPORT_FAILED
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_ACTION_IMPORT_ONGOING
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_ACTION_IMPORT_SUCCESS
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_INTENT_DATA
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_INTENT_EXTRA
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_INTENT_STATUS
import uz.behzod.eightytwenty.utils.constants.ServiceConstants.FILE_ATTACHMENT
import uz.behzod.eightytwenty.utils.constants.ServiceConstants.SERVICE_ACTION_CANCEL
import uz.behzod.eightytwenty.utils.constants.ServiceConstants.SERVICE_ACTION_START
import uz.behzod.eightytwenty.utils.constants.ServiceConstants.SERVICE_ACTION_WITH_BROADCAST
import uz.behzod.eightytwenty.utils.constants.ServiceConstants.SERVICE_EXTRA_UID
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.extension.getFileName
import java.io.File
import java.lang.Exception

class FileImportService : Service() {

    private lateinit var targetFolder: File

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            SERVICE_ACTION_START -> {
                printDebug { "[FileImportService]: Service is started" }
                targetFolder = File(getExternalFilesDir(null), FILE_ATTACHMENT)
                intent.data?.let {
                    onReproduce(it, intent.getStringExtra(SERVICE_EXTRA_UID)!!)
                }
            }
            SERVICE_ACTION_CANCEL -> {
                printDebug { "[FileImportService]: Service is canceled" }
                terminateService()
            }

        }
        return START_REDELIVER_INTENT
    }

    private fun onReproduce(uri: Uri, uid: String) {
        printDebug { "[FileImportService]: onReproduce() is started" }

        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            terminateService(BROADCAST_ACTION_IMPORT_FAILED)
            return
        }
        sendLocalBroadcast(BROADCAST_ACTION_IMPORT_ONGOING)

        try {
            contentResolver.openInputStream(uri)?.use {
                val fileName = uri.getFileName(this)
                val extension = File(fileName).extension

                val targetFile = File(targetFolder, "${uid},${extension}")
                FileUtils.copyToFile(it, targetFile)

                broadcastResultThenTerminate(uid, fileName)
            }
        } catch (e: Exception) {

            e.printStackTrace()
            terminateService(status = BROADCAST_ACTION_IMPORT_FAILED)
        }

    }

    private fun terminateService(status: String? = null, data: String? = null) {
        if (status != null) {
            sendLocalBroadcast(status, data)
        }
        stopSelf()
    }

    private fun sendLocalBroadcast(status: String, data: String? = null) {
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(Intent(SERVICE_ACTION_WITH_BROADCAST).apply {
                printDebug { "[FileImportService]: sendLocalBroadcast() is started" }
                putExtra(BROADCAST_INTENT_STATUS, status)
                if (data != null)
                    printDebug { "[FileImportService]: Data is not null" }
                putExtra(BROADCAST_INTENT_DATA, data)
            })
    }


    /**
     *  Sends the required data back to the activity then
     *  terminates itself.
     *  @param id the attachment id that the file will be linked in
     *  @param name the original file name of the file
     */

    private fun broadcastResultThenTerminate(id: String, name: String) {
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(Intent(SERVICE_ACTION_WITH_BROADCAST).apply {
                printDebug { "[FileImportService]: broadcastResultThenTerminate is started" }
                // This function is only called when the import is
                // completed and therefore we should just
                // put a completed status in the broadcast
                putExtra(BROADCAST_INTENT_STATUS, BROADCAST_ACTION_IMPORT_SUCCESS)

                // Send the attachment id back to the calling activity
                putExtra(BROADCAST_INTENT_DATA, id)

                // Send the file name back to the calling activity
                putExtra(BROADCAST_INTENT_EXTRA, name)
            })
        printDebug { "[FileImportService]: broadcastResultThenTerminate is stopped" }
        stopSelf()
        printDebug { "[FileImportService]: broadcastResultThenTerminate is stopped" }
    }

}

