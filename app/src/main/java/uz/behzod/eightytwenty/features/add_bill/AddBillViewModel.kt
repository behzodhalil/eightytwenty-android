package uz.behzod.eightytwenty.features.add_bill

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.behzod.eightytwenty.core.ReduxViewModel
import javax.inject.Inject

@HiltViewModel
class AddBillViewModel @Inject constructor(): ReduxViewModel<AddBillState>(initialState = AddBillState.empty) {

    fun modifyBillName(value: String) {

    }

    fun insertBill() {

    }
}