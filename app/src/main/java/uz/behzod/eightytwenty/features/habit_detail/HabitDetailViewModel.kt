package uz.behzod.eightytwenty.features.habit_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import uz.behzod.eightytwenty.domain.interactor.habit.FetchHabitByUid
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import javax.inject.Inject

@HiltViewModel
class HabitDetailViewModel @Inject constructor(
    private val iFetchHabitByUid: FetchHabitByUid
) : ViewModel() {

    private var _uiState: MutableStateFlow<HabitDetailUiState> =
        MutableStateFlow(HabitDetailUiState.Loading)
    val uiState: Flow<HabitDetailUiState> = _uiState.asStateFlow()

    fun fetchHabitByUid(uid: Long) {
        iFetchHabitByUid.invoke(uid).onEach { habit: HabitDomainModel? ->
            _uiState.value = HabitDetailUiState.Loading
            if (habit!= null) {
                _uiState.value = HabitDetailUiState.Success(habit)
            } else {
                _uiState.value = HabitDetailUiState.Empty
            }
        }.launchIn(viewModelScope)
    }
}