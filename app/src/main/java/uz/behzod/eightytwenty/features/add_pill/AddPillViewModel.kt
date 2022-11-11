package uz.behzod.eightytwenty.features.add_pill

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity
import uz.behzod.eightytwenty.domain.interactor.pill.InsertPill
import javax.inject.Inject

@HiltViewModel
class AddPillViewModel @Inject constructor(
    private val defaultInsertPill: InsertPill,
) : ReduxViewModel<AddPillState>(initialState = AddPillState.empty) {

    /**
     * This method helps to update name of the pill.
     */
    fun reduceName(value: String) {
        modifyState { state -> state.copy(name = value) }
    }

    /**
     * This method helps to update duration of the pill.
     */
    fun reduceDuration(value: String) {
        modifyState { state -> state.copy(duration = value) }
    }

    /**
     * This method helps to update dose of the pill.
     */
    fun reduceDose(value: Long) {
        modifyState { state -> state.copy(dose = value) }
    }

    /**
     * This method helps to update frequency of the pill.
     */
    fun reduceFrequency(value: String) {
        modifyState { state -> state.copy(frequency = value) }
    }

    /**
     * This method helps to update form of the pill.
     */
    fun reduceForm(value: String) {
        modifyState { state -> state.copy(form = value) }
    }

    /**
     * This method helps to insert the pill to local database.
     *
     * It used [runCatching]for reducing boilerplate code.
     * If the result is successful, the block of code is run on the onSuccess.
     * If the result is a failure, the block of code is run on the onFailure.
     */
    fun insertPill() = viewModelScope.launch {
        val name = currentState.name
        val duration = currentState.duration
        val form = currentState.form
        val frequency = currentState.frequency
        val dose = currentState.dose

        runCatching {
            defaultInsertPill.execute(
                PillEntity(
                    duration = duration,
                    name = name,
                    form = form,
                    frequency = frequency,
                    dose = dose
                )
            )
        }.onSuccess {
            modifyState { state ->
                state.copy(isSaved = true)
            }
        }.onFailure {
            modifyState { state ->
                state.copy(isSaveFailed = true)
            }
        }
    }

}