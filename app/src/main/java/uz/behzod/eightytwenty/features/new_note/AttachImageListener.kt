package uz.behzod.eightytwenty.features.new_note

import android.net.Uri

interface AttachImageListener {
    fun addImages(uriSource: UriSources)
}

typealias UriSources = List<Uri>