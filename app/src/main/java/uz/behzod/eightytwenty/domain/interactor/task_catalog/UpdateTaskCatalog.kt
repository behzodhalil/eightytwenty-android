package uz.behzod.eightytwenty.domain.interactor.task_catalog

import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

interface UpdateTaskCatalog {
    suspend operator fun invoke(param: TaskCatalogEntity)
}