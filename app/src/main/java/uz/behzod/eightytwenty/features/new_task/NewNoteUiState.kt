package uz.behzod.eightytwenty.features.new_task

import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

sealed interface NewNoteUiState {
    object Success : NewNoteUiState
    object Failure : NewNoteUiState
    object Empty : NewNoteUiState
    object Loading : NewNoteUiState
}

fun failure(): NewNoteUiState {
    return NewNoteUiState.Failure
}

fun success(): NewNoteUiState {
    return NewNoteUiState.Success
}

fun loading(): NewNoteUiState {
    return NewNoteUiState.Loading
}

data class NewTaskViewState(
    val tasks: List<TaskEntity> = emptyList(),
    val notes: List<NoteDomainModel> = emptyList(),
    val schedules: List<ScheduleEntity> = emptyList(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false
)
