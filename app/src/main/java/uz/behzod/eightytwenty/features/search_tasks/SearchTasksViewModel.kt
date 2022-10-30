package uz.behzod.eightytwenty.features.search_tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.task.SearchTasks
import javax.inject.Inject

/**
 * Construct a new [SearchTasksViewModel] an instance.
 *
 */

@HiltViewModel
class SearchTasksViewModel @Inject constructor(
    private val iSearchTasks: SearchTasks
) : ViewModel() {

    private var _uiState: MutableStateFlow<SearchTasksUiState> =
        MutableStateFlow(SearchTasksUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun searchAndGetTasks(name: String) {
        _uiState.value = SearchTasksUiState.Loading
        viewModelScope.launch {
            iSearchTasks.invoke(taskName = name).collect {
                if (it.isNotEmpty()) {
                    _uiState.value = SearchTasksUiState.Success(it)
                } else {
                    _uiState.value = SearchTasksUiState.Empty
                }
            }
        }

    }

}