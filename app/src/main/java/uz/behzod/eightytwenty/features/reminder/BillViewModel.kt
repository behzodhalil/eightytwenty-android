package uz.behzod.eightytwenty.features.reminder

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.bill.FetchBills
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(
    fetchBills: FetchBills,
) : ReduxViewModel<BillState>(initialState = BillState()) {

    init {
        fetchBills.execute().onEach {
            modifyState { state ->
                state.copy(
                    isLoaded = true
                )
            }

            if (it.isNotEmpty()) {
                modifyState { state ->
                    state.copy(
                        bills = it,
                        isSuccess = true,
                        isLoaded = false
                    )
                }
                Log.d("ViewModel","Bills are $it")
            } else {
                modifyState { state ->
                    state.copy(
                        isSuccess = false,
                        isEmpty = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
