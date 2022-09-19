package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.domain.repository.TaskCatalogRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class IncrementTaskCountImpl @Inject constructor(
    private val repository: TaskCatalogRepository,
    private val dispatchers: IDispatcherProvider
): IncrementTaskCount {

    override suspend fun invoke(uid: Long) {
        return withContext(dispatchers.io) {
            repository.incrementTaskCount(uid)
        }
    }
}