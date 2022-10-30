package uz.behzod.eightytwenty.features.new_note

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.note.InsertNote
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.getUriExtension
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.manager.ImageStorageManager
import java.io.File
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val iInsertNote: InsertNote
) : ReduxViewModel<NewNoteViewState>(initialState = NewNoteViewState()) {

    fun modifyTitle(title: String) {
        modifyState { state -> state.copy(title = title) }
    }

    fun modifyDesc(desc: String) {
        modifyState { state -> state.copy(description = desc) }
    }

    fun modifyTimestamp(timestamp: ZonedDateTime) {
        modifyState { state -> state.copy(timestamp = timestamp) }
    }

    fun modifyIsTrashed(isTrashed: Boolean) {
        modifyState { state -> state.copy(isTrashed = isTrashed) }
    }

    fun modifyGroupUid(uid: Long) {
        modifyState { state -> state.copy(categoryUid = uid) }
    }

    fun modifyUri(uri: Uri?) {
        modifyState { state -> state.copy(uri = uri) }
    }

    fun insertNote() {
        viewModelScope.launch {
            val title = state.value.title.trim()
            val desc = state.value.description.trim()
            val timestamp = state.value.timestamp
            val isTrashed = state.value.isTrashed
            val images = state.value.images

            modifyState { state -> state.copy(isLoading = true) }

            runCatching {
                iInsertNote.invoke(
                    NoteDomainModel(
                        title = title,
                        description = desc,
                        timestamp = timestamp,
                        isTrashed = isTrashed
                    ), images
                )
                printDebug { "[Test Image] Images are $images" }
            }.onSuccess {
                modifyState { state ->
                    state.copy(
                        isSuccess = true,
                        isFailure = false,
                        isLoading = false,
                    )
                }
            }.onFailure {
                modifyState { state -> state.copy(isFailure = true) }
            }
        }
    }


    private suspend fun updateUriSource(context: Context, uriSource: Uri): Uri {
        val ext = context.getUriExtension(uriSource) ?: "jpeg"

        val fileName = buildString {
            append("IMG_")
            append(
                java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
                    .format(LocalDateTime.now())
            )
            append("_${(0..999).random()}.$ext")
        }
        printDebug { "" }

        val fullPath = ImageStorageManager.saveImage(context, uriSource, fileName)
        val file = File(fullPath)
        return FileProvider.getUriForFile(context, "uz.behzod.eightytwenty", file)
    }
}