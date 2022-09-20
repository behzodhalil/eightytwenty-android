package uz.behzod.eightytwenty.features.task_catalog

import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

sealed class TaskCatalogUiState {
    data class Success(val data: List<TaskCatalogEntity>): TaskCatalogUiState()
    data class Failure(val message: String): TaskCatalogUiState()
    object Empty: TaskCatalogUiState()
    object Loading: TaskCatalogUiState()
}
