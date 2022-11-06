package uz.behzod.eightytwenty.features.select_productivity

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.task.FetchLimitedTasks
import uz.behzod.eightytwenty.domain.interactor.task.FetchTasksNearTime
import uz.behzod.eightytwenty.domain.interactor.task.FetchTasksRecent
import uz.behzod.eightytwenty.utils.extension.printDebug
import javax.inject.Inject

@HiltViewModel
class SelectTaskProductivityViewModel @Inject constructor(
    defaultFetchLimitedTasks: FetchLimitedTasks,
    defaultFetchTasksRecent: FetchTasksRecent,
    defaultFetchTasksNearTime: FetchTasksNearTime,
) : ReduxViewModel<SelectTaskState>(
    initialState = SelectTaskState.empty()
) {

    init {
        defaultFetchLimitedTasks.execute().onEach { tasks ->
            if (tasks.isNotEmpty()) {

                modifyState { state ->
                    state.copy(
                        tasksLimited = tasks
                    )
                }
            }
            printDebug { "[VM]: Limited tasks: $tasks" }
        }.launchIn(viewModelScope)

        defaultFetchTasksRecent.execute().onEach { tasks ->
            if (tasks.isNotEmpty()) {
                modifyState { state ->
                    state.copy(
                        tasksRecent = tasks
                    )
                }
            }
            printDebug { "[VM]: Recent tasks: $tasks" }
        }.launchIn(viewModelScope)

        defaultFetchTasksNearTime.execute().onEach { tasks ->
            if (tasks.isNotEmpty()) {
                modifyState { state ->
                    state.copy(
                        tasksNearTime = tasks
                    )

                }
                printDebug { "Tasks near time $tasks" }
            }
        }.launchIn(viewModelScope)
    }

}