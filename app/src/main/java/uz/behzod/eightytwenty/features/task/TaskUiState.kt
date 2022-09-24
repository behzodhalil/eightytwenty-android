package uz.behzod.eightytwenty.features.task

import uz.behzod.eightytwenty.data.local.entities.TaskEntity

sealed class TaskUiState {
    data class Success(val data: List<TaskEntity>): TaskUiState()
    data class Failure(val msg: String): TaskUiState()
    object Empty: TaskUiState()
    object Loading: TaskUiState()
}

fun success(data: List<TaskEntity>): TaskUiState {
    return TaskUiState.Success(data)
}

fun loading(): TaskUiState {
    return TaskUiState.Loading
}

fun empty(): TaskUiState {
    return TaskUiState.Empty
}