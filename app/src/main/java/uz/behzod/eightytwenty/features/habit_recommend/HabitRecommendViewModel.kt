package uz.behzod.eightytwenty.features.habit_recommend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.habit_recommend.FetchHabitRecommendsByCategory
import javax.inject.Inject

@HiltViewModel
class HabitRecommendViewModel @Inject constructor(
    private val iFetchHabitRecommendsByCategory: FetchHabitRecommendsByCategory
) : ViewModel() {

    private val _uiState: MutableStateFlow<HabitRecommendUIState> =
        MutableStateFlow(HabitRecommendUIState.Loading)
    val uiState = _uiState.asStateFlow()


    fun fetchHabitRecommendByCategory(category: String) {
        viewModelScope.launch {
            iFetchHabitRecommendsByCategory.invoke(category)
                .onEach {
                    _uiState.value = HabitRecommendUIState.Loading
                    if (it.isNotEmpty()) {
                        _uiState.value = HabitRecommendUIState.Success(it)
                    } else {
                        _uiState.value = HabitRecommendUIState.Empty
                    }
                }
                .launchIn(viewModelScope)
        }
    }

}