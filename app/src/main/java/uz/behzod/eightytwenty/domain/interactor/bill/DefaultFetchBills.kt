package uz.behzod.eightytwenty.domain.interactor.bill

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.domain.repository.BillRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchBills @Inject constructor(
    private val billRepository: BillRepository,
    private val dispatchers: IDispatcherProvider
): FetchBills {

    override fun execute(): Flow<List<BillEntity>> {
        return flow {
            billRepository.fetchBills().collect {
                if (it.isNotEmpty()) {
                    emit(it)
                }
            }
        }.flowOn(dispatchers.io)
    }


}