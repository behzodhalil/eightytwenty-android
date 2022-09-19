package uz.behzod.eightytwenty.features.new_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.domain.interactor.task_catalog.FetchTaskCatalogs
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val iFetchTaskCatalogs: FetchTaskCatalogs
) : ViewModel() {

    private var _uiState: MutableStateFlow<CatalogUiState> =
        MutableStateFlow(CatalogUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        iFetchTaskCatalogs.invoke().onEach {
            _uiState.value = CatalogUiState.Loading
            if (it.isNotEmpty()) {
                _uiState.value = CatalogUiState.Success(it)
            } else {
                _uiState.value = CatalogUiState.Empty
            }
        }.launchIn(viewModelScope)
    }

}