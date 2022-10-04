package uz.behzod.eightytwenty.features.task_group

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

sealed class TaskCatalogUiState {
    data class Success(val data: List<TaskCatalogEntity>): TaskCatalogUiState()
    data class Failure(val message: String): TaskCatalogUiState()
    object Empty: TaskCatalogUiState()
    object Loading: TaskCatalogUiState()
}

data class TaskGroupState(
    val taskGroups: List<TaskCatalogEntity> = emptyList(),
    val taskGroupName: String = String.Empty,
    val taskCount: Long = Long.Zero,
    val taskUid: Int = Int.Zero,
    val isAdded: Boolean = false,
    val isAddedFailure: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isLoading: Boolean = false,
    val isDisplayedNewGroup: Boolean = false,

): ViewState
