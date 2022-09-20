package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.domain.repository.TaskCatalogRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class SearchTaskCatalogImpl @Inject constructor(
    private val repository: TaskCatalogRepository,
    private val dispatchers: IDispatcherProvider
): SearchTaskCatalog {

    override fun invoke(catalogName: String): Flow<List<TaskCatalogEntity>> {
        return flow {
            repository.searchCatalog(catalogName).collect {
                emit(it)
            }
        }.flowOn(dispatchers.io)
    }
}