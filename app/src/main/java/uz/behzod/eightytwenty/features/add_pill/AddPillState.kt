package uz.behzod.eightytwenty.features.add_pill

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

data class AddPillState(
    val duration: String = String.Empty,
    val frequency: String = String.Empty,
    val dose: Long = Long.Zero,
    val form: String = String.Empty,
    val name: String = String.Empty,
    val isSaved: Boolean = false,
    val isSaveFailed: Boolean = false
): ViewState {
    companion object {
        val empty = AddPillState()
    }
}
