package uz.behzod.eightytwenty.features.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.task.FetchTasks
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val iFetchTasks: FetchTasks
): ViewModel() {

    private var _uiState: MutableStateFlow<TaskUiState> = MutableStateFlow(loading())
    val uiState: Flow<TaskUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            iFetchTasks.invoke().collect {
                _uiState.value = TaskUiState.Loading
                if (it.isEmpty()) {
                    _uiState.value = empty()
                } else {
                    _uiState.value = TaskUiState.Success(it)
                }
            }
        }
    }

}