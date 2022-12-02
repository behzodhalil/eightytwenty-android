package uz.behzod.eightytwenty.features.add_water

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity
import uz.behzod.eightytwenty.domain.interactor.water.InsertWater
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class AddWaterViewModel @Inject constructor(
    private val insertWater: InsertWater,
) : ReduxViewModel<AddWaterState>(initialState = AddWaterState()) {

    fun reduceQuantity(value: Long) {
        modifyState { state -> state.copy(quantity = value) }
    }

    fun reduceFrequency(value: String) {
        modifyState { state -> state.copy(frequency = value) }
    }

    fun reduceReminderTime(value: ZonedDateTime) {
        modifyState { state -> state.copy(reminderTime = value) }
    }

    fun isDatePicked(value: Boolean) {
        modifyState { state -> state.copy(isDatePicked = value) }
    }

    fun insertWater() = viewModelScope.launch {
        val quantity = currentState.quantity
        val frequency = currentState.frequency
        val reminderTime = currentState.reminderTime
        val timestamp = ZonedDateTime.now()

        val water = WaterEntity(
            quantity = quantity,
            frequency = frequency,
            timestamp = timestamp,
            reminderTime = reminderTime
        )

        runCatching {
            insertWater.insertWater(water)
        }.onSuccess {
            modifyState { state ->
                state.copy(
                    isSaved = true
                )
            }
        }.onFailure {
            modifyState { state ->
                state.copy(
                    isSaveFailed = false
                )
            }
        }
    }
}
