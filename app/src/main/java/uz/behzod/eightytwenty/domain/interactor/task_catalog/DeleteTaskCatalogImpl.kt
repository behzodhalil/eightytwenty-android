package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.domain.repository.TaskCatalogRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DeleteTaskCatalogImpl @Inject constructor(
    private val repository: TaskCatalogRepository,
    private val dispatchers: IDispatcherProvider
): DeleteTaskCatalog {

    override suspend fun invoke(param: TaskCatalogEntity) {
        return withContext(dispatchers.io) {
            repository.deleteTaskCatalog(param)
        }
    }
}