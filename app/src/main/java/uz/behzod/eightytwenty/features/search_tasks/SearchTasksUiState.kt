package uz.behzod.eightytwenty.features.search_tasks

import uz.behzod.eightytwenty.data.local.entities.TaskEntity

sealed class SearchTasksUiState {
    data class Success(val tasks: List<TaskEntity>): SearchTasksUiState()
    data class Failure(val message: String) : SearchTasksUiState()
    object Empty: SearchTasksUiState()
    object Loading: SearchTasksUiState()
}
