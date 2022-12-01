package uz.behzod.eightytwenty.features.new_note

import android.net.Uri
import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

data class NewNoteViewState(
    val title: String = String.Empty,
    val description: String = String.Empty,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    val isTrashed: Boolean = false,
    val noteUid: Long = Long.Zero,
    val uri: Uri? = null,
    var images: List<NoteImageEntity> = emptyList(),
    var noteRelation: NoteRelation? = null,
    var uriSource: List<Uri> = emptyList(),
    val categoryUid: Long = 1L,
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val isSuccess: Boolean = false
): ViewState
