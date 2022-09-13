package uz.behzod.eightytwenty.features.habit

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import uz.behzod.eightytwenty.domain.interactor.habit.FetchHabitsByDate
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val iFetchHabitsByDate: FetchHabitsByDate
) : ViewModel() {

    private var _uiState: MutableStateFlow<HabitUIState> = MutableStateFlow(HabitUIState.Loading)
    val uiState: Flow<HabitUIState> = _uiState.asStateFlow()

    val mutableDates : MutableLiveData<String> = MutableLiveData()

    val habits: LiveData<List<HabitDomainModel>> = Transformations.switchMap(mutableDates) { date ->
        iFetchHabitsByDate.invoke(date).asLiveData()

    }

    fun searchByTimestamp(timestamp: String) {
        mutableDates.value = timestamp
    }

    fun fetchHabitsByDate(date: String) {
        iFetchHabitsByDate.invoke(date).onEach { habit ->
            _uiState.update {
               HabitUIState.Loading
            }
            if (habit.isNotEmpty()) {
                _uiState.update {
                    HabitUIState.Success(habit)
                }
            } else if (habit.isNullOrEmpty()) {
                _uiState.update {
                    HabitUIState.Empty
                }
            }
        }.catch {
            Log.d("Tag","Error is occured")
        }
            .launchIn(viewModelScope)
    }

}
