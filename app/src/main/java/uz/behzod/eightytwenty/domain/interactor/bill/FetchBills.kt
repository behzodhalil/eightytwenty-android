package uz.behzod.eightytwenty.domain.interactor.bill

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity

interface FetchBills {
    fun execute(): Flow<List<BillEntity>>
}