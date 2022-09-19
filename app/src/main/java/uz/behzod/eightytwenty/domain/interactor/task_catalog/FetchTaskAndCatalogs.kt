package uz.behzod.eightytwenty.domain.interactor.task_catalog

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.CatalogAndTasks

interface FetchTaskAndCatalogs {
    operator fun invoke(): Flow<List<CatalogAndTasks>>
}