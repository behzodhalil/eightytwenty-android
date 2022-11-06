package uz.behzod.eightytwenty.features.select_productivity

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.TaskEntity

data class SelectTaskState(
    val tasksLimited: List<TaskEntity> = emptyList(),
    val tasksNearTime: List<TaskEntity> = emptyList(),
    val tasksRecent: List<TaskEntity> = emptyList()
): ViewState {
    companion object {
        fun empty(): SelectTaskState {
            return SelectTaskState()
        }
    }
}
