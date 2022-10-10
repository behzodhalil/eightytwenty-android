package uz.behzod.eightytwenty.features.new_note

import androidx.activity.result.ActivityResultCallback

class PickImagesCallback(
    private val listener: AttachImageListener
) : ActivityResultCallback<UriSources> {

    override fun onActivityResult(result: UriSources?) {
        if (!result.isNullOrEmpty()) {
            listener.addImages(result)
        }
    }
}