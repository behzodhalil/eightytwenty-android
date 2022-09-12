package uz.behzod.eightytwenty.features.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import uz.behzod.eightytwenty.domain.interactor.habit.FetchHabitsByDate
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val iFetchHabitsByDate: FetchHabitsByDate
) : ViewModel() {

    private var _uiState: MutableStateFlow<HabitUIState> = MutableStateFlow(HabitUIState.Loading)
    val uiState: Flow<HabitUIState> = _uiState.asStateFlow()

    fun fetchHabitsByDate(date: String) {
        iFetchHabitsByDate.invoke(date).onEach { habit ->
            _uiState.value = HabitUIState.Loading
            if (habit.isNotEmpty()) {
                _uiState.value = HabitUIState.Success(habit)
            } else {
                _uiState.value = HabitUIState.Empty
            }
        }.launchIn(viewModelScope)
    }
}