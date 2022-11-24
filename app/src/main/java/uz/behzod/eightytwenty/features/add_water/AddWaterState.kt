package uz.behzod.eightytwenty.features.add_water

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

data class AddWaterState(
    val quantity: Int = Int.Zero,
    val image: Int = Int.Zero,
    val frequency: String = String.Empty,
    val isSaved: Boolean = false,
    val isSaveFailed: Boolean  = false
): ViewState
