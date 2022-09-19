package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.CatalogAndTasks
import uz.behzod.eightytwenty.domain.repository.TaskCatalogRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchTaskAndCatalogsImpl @Inject constructor(
    private val repository: TaskCatalogRepository,
    private val dispatchers: IDispatcherProvider
): FetchTaskAndCatalogs {

    override fun invoke(): Flow<List<CatalogAndTasks>> {
        return flow {
            repository.fetchTaskAndCatalogs().collect {
                emit(it)
            }
        }.flowOn(dispatchers.io)
    }
}