package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.domain.repository.TaskCatalogRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchTaskCatalogsImpl @Inject constructor(
    private val repository: TaskCatalogRepository,
    private val dispatchers: IDispatcherProvider
) : FetchTaskCatalogs {

    override fun invoke(): Flow<List<TaskCatalogEntity>> {
        return flow {
            repository.fetchTaskCatalogs().collect {
                emit(it)
            }
        }.flowOn(dispatchers.io)
    }
}