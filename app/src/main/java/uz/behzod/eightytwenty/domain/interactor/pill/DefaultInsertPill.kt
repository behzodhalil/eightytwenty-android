package uz.behzod.eightytwenty.domain.interactor.pill

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity
import uz.behzod.eightytwenty.domain.repository.PillRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultInsertPill @Inject constructor(
    private val pillRepository: PillRepository,
    private val dispatchers: IDispatcherProvider
): InsertPill {

    override suspend fun execute(pill: PillEntity) {
        return withContext(dispatchers.io) {
            pillRepository.insertPill(pill)
        }
    }
}