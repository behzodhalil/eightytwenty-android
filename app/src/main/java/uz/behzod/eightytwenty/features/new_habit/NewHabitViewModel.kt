package uz.behzod.eightytwenty.features.new_habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.domain.interactor.habit.InsertHabit
import uz.behzod.eightytwenty.domain.interactor.habit_recommend.FetchHabitRecommendByUid
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import javax.inject.Inject

@HiltViewModel
class NewHabitViewModel @Inject constructor(
    private val iFetchHabitRecommendByUid: FetchHabitRecommendByUid,
    private val iInsertHabitInteractor: InsertHabit
) : ViewModel() {

    private var _uiState: MutableStateFlow<NewHabitUiState> =
        MutableStateFlow(NewHabitUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchHabitRecommendByUid(uid: Long) {
        viewModelScope.launch {
            _uiState.value = NewHabitUiState.Loading
            iFetchHabitRecommendByUid.invoke(uid)
                .collect { habitRecommend: HabitRecommendDomainModel? ->
                    if (habitRecommend != null) {
                        _uiState.value = NewHabitUiState.Success(habitRecommend)
                    }
                }
        }
    }

    fun insertHabit(habit: HabitDomainModel, scheduleEntity: List<ScheduleEntity>) {
        viewModelScope.launch {
            iInsertHabitInteractor.invoke(habit,scheduleEntity)
        }
    }
}