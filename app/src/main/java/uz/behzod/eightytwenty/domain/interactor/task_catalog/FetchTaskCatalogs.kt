package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

interface FetchTaskCatalogs {
    operator fun invoke(): Flow<List<TaskCatalogEntity>>
}