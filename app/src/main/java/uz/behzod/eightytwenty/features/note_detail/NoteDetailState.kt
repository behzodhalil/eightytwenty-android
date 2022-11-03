package uz.behzod.eightytwenty.features.note_detail

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

data class NoteDetailState(
    val notes: List<NoteDomainModel> = emptyList(),
    val note: NoteRelation? = null,
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val isSuccess: Boolean = false,
    val isUpdated: Boolean = false,
    val isUpdateFailed: Boolean = false,
    val isDeleted: Boolean = false,
    val isDeleteFailed: Boolean = false,
    val isMoved: Boolean = false,
    val isMoveFailed: Boolean = false,
    val title: String = String.Empty,
    val description: String = String.Empty,
    val categoryUid: Long = Long.Zero,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    val images: List<NoteImageEntity> = emptyList(),
    val uid: Long = Long.Zero
) : ViewState
