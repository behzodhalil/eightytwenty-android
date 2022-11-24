package uz.behzod.eightytwenty.features.add_water

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.utils.constants.Water
import javax.inject.Inject

@HiltViewModel
class AddWaterViewModel @Inject constructor(

): ReduxViewModel<AddWaterState>(initialState = AddWaterState()) {

    fun reduceQuantity(value: Int) {
        modifyState { state -> state.copy(quantity = value) }
    }

    fun reduceImage(value: Int) {
        modifyState { state -> state.copy(image = value) }
    }


    fun reduceFrequency(value: String) {
        modifyState { state -> state.copy(frequency = value) }
    }

    fun insertWater() {
        val quantity = currentState.quantity
        val image = currentState.image
        val frequency = currentState.frequency
    }
}
