package uz.behzod.eightytwenty.features.new_task

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

data class NewTaskState(
    val noteTitle: String = String.Empty,
    val noteDesc: String = String.Empty,
    val noteTimestamp: String = String.Empty,
    val noteIsTrashed: Boolean = false,
    val attachmentTitle: String = String.Empty,
    val taskTitle: String = String.Empty,
    val taskTimestamp: ZonedDateTime? = null,
    val taskGroupName: String = String.Empty,
    val taskCatalogUid: Long = Long.Zero,
    val taskReminder: ZonedDateTime? = null,
    val taskCompletionDate: ZonedDateTime? = null,
    val taskIsTrashed: Boolean = false,
    val taskIsComplete: Boolean = false,
    var scheduleDaysOfWeek: Int = Int.Zero,
    val scheduleFrequencyType: Int = Int.Zero,
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val isSuccess: Boolean = false,
    val isReadStorePermission: Boolean = false,
    val isLoadedAttachment: Boolean = false,
    val isDisplayedSingleNote: Boolean = false,
    val isDisplayedCompletionDate: Boolean = false,
    val isDisplayedReminder: Boolean = false,
    val isDisplayedRepeat: Boolean = false,
    val isDisplayedGroup: Boolean = false,
): ViewState
