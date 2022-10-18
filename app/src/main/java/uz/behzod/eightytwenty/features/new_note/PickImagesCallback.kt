package uz.behzod.eightytwenty.features.new_note

import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import uz.behzod.eightytwenty.utils.extension.printDebug

class PickImagesCallbacks(
    private val listener: AttachImageListeners
) : ActivityResultCallback<List<Uri>> {

    override fun onActivityResult(result: List<Uri>?) {
        result?.let {
            listener.addImages(it)
        }
    }
}