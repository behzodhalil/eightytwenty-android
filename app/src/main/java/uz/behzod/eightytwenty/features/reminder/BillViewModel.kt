package uz.behzod.eightytwenty.features.reminder

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.behzod.eightytwenty.core.ReduxViewModel
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(
): ReduxViewModel<BillState>(initialState = BillState()) {

    init {

    }
}