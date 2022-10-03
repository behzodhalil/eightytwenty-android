package uz.behzod.eightytwenty.features.new_note

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

data class NewNoteViewState(
    val title: String = String.Empty,
    val description: String = String.Empty,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    val isTrashed: Boolean = false,
    val categoryUid: Long = Long.Zero,
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val isSuccess: Boolean = false
): ViewState
