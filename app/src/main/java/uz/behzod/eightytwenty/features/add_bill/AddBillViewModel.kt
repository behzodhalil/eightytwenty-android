package uz.behzod.eightytwenty.features.add_bill

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.domain.interactor.bill.InsertBill
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class AddBillViewModel @Inject constructor(
    private val insertBill: InsertBill,
) : ReduxViewModel<AddBillState>(initialState = AddBillState.empty) {

    fun reduceName(value: String) {
        modifyState { state -> state.copy(name = value) }
    }

    fun reduceAmount(value: Long) {
        modifyState { state -> state.copy(amount = value) }
    }

    fun reduceTimestamp(value: ZonedDateTime) {
        modifyState { state -> state.copy(timestamp = value) }
    }

    fun reduceBiller(value: String) {
        modifyState { state -> state.copy(biller = value) }
    }

    fun isDatePicked(value: Boolean) {
        modifyState { state -> state.copy(isDatePicked = value) }
    }

    fun insertBill() = viewModelScope.launch {
        val name = currentState.name
        val amount = currentState.amount
        val timestamp = currentState.timestamp
        val biller = currentState.biller

        val bill = BillEntity(name = name, amount = amount, timestamp = timestamp, type = biller)

        runCatching {
            insertBill.execute(bill)
        }.onSuccess {
            modifyState { state ->
                state.copy(isSaved = true)
            }
        }.onFailure {
            modifyState { state ->
                state.copy(
                    isSaved = false,
                    isSaveFailed = true
                )
            }
        }

    }
}
