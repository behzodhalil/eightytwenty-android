package uz.behzod.eightytwenty.features.search_catalog

import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

sealed class SearchCatalogUiState {
    data class Success(val data: List<TaskCatalogEntity>): SearchCatalogUiState()
    data class Failure(val message: String): SearchCatalogUiState()
    object Empty: SearchCatalogUiState()
    object Loading: SearchCatalogUiState()
}
