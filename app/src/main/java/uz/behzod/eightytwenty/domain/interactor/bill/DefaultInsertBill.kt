package uz.behzod.eightytwenty.domain.interactor.bill

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.domain.repository.BillRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultInsertBill @Inject constructor(
    private val billRepository: BillRepository,
    private val dispatchers: IDispatcherProvider
): InsertBill {

    override suspend fun execute(bill: BillEntity): Long {
        return withContext(dispatchers.io) {
            billRepository.insertBill(bill)
        }
    }
}