package uz.behzod.eightytwenty.domain.interactor.task

import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

interface InsertTask {
    suspend operator fun invoke(task: TaskEntity, list: List<NoteDomainModel>)
}