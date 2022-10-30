package uz.behzod.eightytwenty.features.task_group

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.domain.interactor.task_catalog.FetchTaskCatalogs
import uz.behzod.eightytwenty.domain.interactor.task_catalog.InsertTaskCatalog
import javax.inject.Inject

@HiltViewModel
class TaskGroupViewModel @Inject constructor(
    fetchGroups: FetchTaskCatalogs,
    private val insertGroup: InsertTaskCatalog
) : ReduxViewModel<TaskGroupState>(initialState = TaskGroupState()) {

    init {
        fetchGroups.invoke()
            .onStart {
                modifyState { state -> state.copy(isLoading = true) }
            }
            .onEach { groups ->
                if (groups.isNotEmpty()) {
                    modifyState { state ->
                        state.copy(
                            taskGroups = groups,
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                } else {
                    modifyState { state ->
                        state.copy(
                            isLoading = false,
                            isSuccess = false,
                            isFailure = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun modifyTaskGroupName(value: String) {
        modifyState { state -> state.copy(taskGroupName = value) }
    }

    fun insertTaskGroup()  = viewModelScope.launch{
        val title = currentState.taskGroupName
        val taskCount = currentState.taskCount

        runCatching {
            insertGroup.invoke(
                TaskCatalogEntity(
                    name = title,
                    taskCount = taskCount
                )
            )
        }.onSuccess {
            modifyState { state -> state.copy(isAdded = true) }
        }.onFailure {
            modifyState { state -> state.copy(isAddedFailure = true) }
        }


    }
}