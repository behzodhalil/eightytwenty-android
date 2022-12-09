package uz.behzod.eightytwenty.features.task

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.utils.extension.Zero

data class TaskState(
    val tasks: List<TaskEntity> = emptyList(),
    val completedTasks: List<TaskEntity> = emptyList(),
    val folderUid: Long = Long.Zero,
    val isCompleteSuccess: Boolean = false,
    val isCompleteEmpty: Boolean = false,
    val isCompleteLoading: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isEmpty: Boolean = false,
    val isCompleted: Boolean = false,
): ViewState
