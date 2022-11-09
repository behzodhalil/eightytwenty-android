package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity

interface BillRepository {
    suspend fun insertBill(bill: BillEntity):Long
    suspend fun updateBill(bill: BillEntity)
    suspend fun deleteBill(bill: BillEntity)
    fun fetchBills(): Flow<List<BillEntity>>
}