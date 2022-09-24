package uz.behzod.eightytwenty.features.new_task

import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

sealed class NewTaskUiState {
    data class Success(
        val data: TaskEntity,
        val schedule: List<ScheduleEntity>,
        val note: List<NoteDomainModel>
    ) : NewTaskUiState()
    object Empty: NewTaskUiState()
    object Loading: NewTaskUiState()
}
