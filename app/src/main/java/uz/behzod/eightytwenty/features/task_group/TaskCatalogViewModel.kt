package uz.behzod.eightytwenty.features.task_group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.domain.interactor.task_catalog.FetchTaskCatalogs
import uz.behzod.eightytwenty.domain.interactor.task_catalog.InsertTaskCatalog
import javax.inject.Inject

@HiltViewModel
class TaskCatalogViewModel @Inject constructor(
    private val iFetchCatalog: FetchTaskCatalogs,
    private val iInsertTaskCatalog: InsertTaskCatalog
) : ViewModel() {

    private var _uiState: MutableStateFlow<TaskCatalogUiState> =
        MutableStateFlow(TaskCatalogUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        iFetchCatalog.invoke().onEach {
            _uiState.value = TaskCatalogUiState.Loading
            if (it.isNotEmpty()) {
                _uiState.value = TaskCatalogUiState.Success(it)
            } else {
                _uiState.value = TaskCatalogUiState.Empty
            }
        }.launchIn(viewModelScope)
    }

    fun insertCatalog(catalog: TaskCatalogEntity) {
        viewModelScope.launch {
            iInsertTaskCatalog.invoke(param = catalog)
        }
    }
}