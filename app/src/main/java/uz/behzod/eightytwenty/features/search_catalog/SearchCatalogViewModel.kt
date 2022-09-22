package uz.behzod.eightytwenty.features.search_catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.domain.interactor.task_catalog.SearchTaskCatalog
import javax.inject.Inject

@HiltViewModel
class SearchCatalogViewModel @Inject constructor(
    private val iSearchCatalog: SearchTaskCatalog
) : ViewModel() {

    private var _uiState: MutableStateFlow<SearchCatalogUiState> =
        MutableStateFlow(SearchCatalogUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun searchCatalog(catalogName: String) {
        iSearchCatalog.invoke(catalogName).onEach {
            _uiState.value = SearchCatalogUiState.Loading
            if (it.isNotEmpty()) {
                _uiState.value = SearchCatalogUiState.Success(it)
            } else {
                _uiState.value = SearchCatalogUiState.Empty
            }
        }.launchIn(viewModelScope)
    }
}