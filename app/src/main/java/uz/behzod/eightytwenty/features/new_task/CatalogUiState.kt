package uz.behzod.eightytwenty.features.new_task

import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

sealed class CatalogUiState {
    data class Success(val data: List<TaskCatalogEntity>): CatalogUiState()
    data class Failure(val message: String): CatalogUiState()
    object Empty: CatalogUiState()
    object Loading: CatalogUiState()
}
