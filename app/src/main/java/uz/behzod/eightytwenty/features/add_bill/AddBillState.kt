package uz.behzod.eightytwenty.features.add_bill

import uz.behzod.eightytwenty.core.state.ViewState

data class AddBillState(
    val isSuccess: Boolean = false
): ViewState {
    companion object {
        val empty = AddBillState()
    }
}
