package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

interface SearchTaskCatalog {
    operator fun invoke(catalogName: String): Flow<List<TaskCatalogEntity>>
}