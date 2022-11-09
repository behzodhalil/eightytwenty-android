package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.BillRepository
import javax.inject.Inject

class DefaultBillRepository @Inject constructor(
    private val sourceManager: LocalSourceManager
): BillRepository {

    override suspend fun insertBill(bill: BillEntity): Long {
        return sourceManager.insertBill(bill)
    }

    override suspend fun updateBill(bill: BillEntity) {
        return sourceManager.updateBill(bill)
    }

    override suspend fun deleteBill(bill: BillEntity) {
        return sourceManager.deleteBill(bill)
    }

    override fun fetchBills(): Flow<List<BillEntity>> {
        return sourceManager.fetchBills()
    }
}