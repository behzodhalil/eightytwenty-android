package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.domain.repository.TaskCatalogRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DecrementTaskCountImpl @Inject constructor(
    private val repository: TaskCatalogRepository,
    private val dispatcher: IDispatcherProvider
): DecrementTaskCount {

    override suspend fun invoke(uid: Long) {
        withContext(dispatcher.io) {
            repository.decrementTaskCount(uid)
        }
    }
}