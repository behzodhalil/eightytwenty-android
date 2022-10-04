package uz.behzod.eightytwenty.features.new_task

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.entities.AttachmentEntity
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.interactor.manager.ReadStorePermission
import uz.behzod.eightytwenty.domain.interactor.task.InsertTask
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.asZoneDateTime
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class NewTaskWithReduxViewModel @Inject constructor(
    private val insertTask: InsertTask,
    private val readStorePermission: ReadStorePermission
) : ReduxViewModel<NewTaskState, NewTaskAction>(NewTaskState()) {

    init {
        readStorePermission()
    }

    fun modifyNoteTitle(value: String) {
        modifyState { state -> state.copy(noteTitle = value) }
    }

    fun modifyNoteDesc(value: String) {
        modifyState { state -> state.copy(noteDesc = value) }
    }

    fun modifyNoteTimestamp(value: String) {
        modifyState { state -> state.copy(noteTimestamp = value) }
    }

    fun modifyNoteIsTrashed(value: Boolean) {
        modifyState { state -> state.copy(noteIsTrashed = value) }
    }

    fun modifyAttachmentState(value: String, isLoaded: Boolean) {
        modifyState { state -> state.copy(attachmentTitle = value, isLoadedAttachment = isLoaded) }
    }

    fun modifyTaskTitle(value: String) {
        modifyState { state -> state.copy(taskTitle = value) }
    }

    fun modifyTaskGroupName(value: String) {
        modifyState { state -> state.copy(taskGroupName = value) }
    }

    fun modifyTaskTimestamp(value: ZonedDateTime) {
        modifyState { state -> state.copy(taskTimestamp = value) }
    }

    fun modifyTaskReminder(value: ZonedDateTime) {
        modifyState { state -> state.copy(taskReminder = value) }
    }

    fun modifyTaskGroupUid(value: Long) {
        modifyState { state -> state.copy(taskCatalogUid = value) }
    }

    fun modifyTaskCompletionDate(value: ZonedDateTime) {
        modifyState { state -> state.copy(taskCompletionDate = value) }
    }

    fun modifyScheduleDaysOfWeek(value: Int, isChecked: Boolean) {
        viewModelScope.launch {
            modifyState { state ->
                if (isChecked) {
                    state.copy(scheduleDaysOfWeek = state.scheduleDaysOfWeek.plus(value))
                } else {
                    state.copy(scheduleDaysOfWeek = state.scheduleDaysOfWeek.minus(value))
                }
            }
        }
    }

    fun modifyScheduleFrequencyType(value: Int) {
        modifyState { state -> state.copy(scheduleFrequencyType = value) }
    }

    fun hasDisplayedSingleNote(value: Boolean) {
        modifyState { state -> state.copy(isDisplayedSingleNote = value) }
    }

    fun hasDisplayedCompletionDate(value: Boolean) {
        modifyState { state -> state.copy(isDisplayedCompletionDate = value) }
    }

    fun hasDisplayedReminder(value: Boolean) {
        modifyState { state -> state.copy(isDisplayedReminder = value) }
    }

    fun hasDisplayedRepeat(value: Boolean) {
        modifyState { modifyState { state -> state.copy(isDisplayedRepeat = value) } }
    }

    fun hasDisplayedGroup(value: Boolean) {
        modifyState { state -> state.copy(isDisplayedGroup = value) }
    }

    fun insertTask() {
        viewModelScope.launch {
            val noteTitle = state.value.noteTitle
            val noteDesc = state.value.noteDesc
            val noteIsTrashed = state.value.noteIsTrashed
            val noteTimestamp = state.value.noteTimestamp

            val attachmentTitle = state.value.attachmentTitle

            val taskTitle = state.value.taskTitle
            val taskTimestamp = state.value.taskTimestamp
            val taskReminder = state.value.taskReminder
            val taskGroupUid = state.value.taskCatalogUid

            val scheduleDaysOfWeek = state.value.scheduleDaysOfWeek
            val scheduleFrequencyType = state.value.scheduleFrequencyType
            val scheduleCompletionDate = state.value.taskCompletionDate

            val schedules = mutableListOf<ScheduleEntity>()
            val notes = mutableListOf<NoteDomainModel>()
            val attachments = mutableListOf<AttachmentEntity>()

            schedules.add(
                ScheduleEntity(
                    frequencyTypes = scheduleFrequencyType,
                    daysOfWeek = scheduleDaysOfWeek,
                    dateOfCompletion = scheduleCompletionDate.toString()
                )
            )

            notes.add(
                NoteDomainModel(
                    title = noteTitle,
                    description = noteDesc,
                    isTrashed = noteIsTrashed,
                    timestamp = noteTimestamp.asZoneDateTime(),
                )
            )

            attachments.add(AttachmentEntity(attachmentName = attachmentTitle))

            modifyState { state -> state.copy(isLoading = true) }

            runCatching {
                insertTask.invoke(
                    TaskEntity(
                        title = taskTitle,
                        timestamp = taskTimestamp,
                        reminderDateTime = taskReminder,
                        catalogUid = taskGroupUid
                    ),
                    noteList = notes,
                    scheduleList = schedules,
                    attachmentList = attachments
                )
            }.onSuccess {
                modifyState { state -> state.copy(isSuccess = true, isLoading = false) }
            }.onFailure {
                modifyState { state ->
                    state.copy(
                        isFailure = true,
                        isLoading = false,
                        isSuccess = false
                    )
                }
            }

        }
    }

    private fun readStorePermission() {
        readStorePermission.invoke()
        modifyState { state -> state.copy(isReadStorePermission = true) }
    }

}