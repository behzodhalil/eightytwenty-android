package uz.behzod.eightytwenty.features.task

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.task.FetchCompletedTasksByFolderUid
import uz.behzod.eightytwenty.domain.interactor.task.FetchTasksByFolderUid
import javax.inject.Inject

@HiltViewModel
class TaskReduxViewModel @Inject constructor(
    private val defaultFetchTasksByFolderUid: FetchTasksByFolderUid,
    private val defaultFetchCompletedTasksByFolderUid: FetchCompletedTasksByFolderUid
): ReduxViewModel<TaskState>(initialState = TaskState()) {

    fun reduceFolderUid(folderUid: Long) {
        modifyState { state -> state.copy(folderUid = folderUid) }
    }

    fun fetchTasksByFolderUid() {
        val currentFolderUid = currentState.folderUid

        defaultFetchTasksByFolderUid.execute(currentFolderUid)
            .onStart { modifyState { state -> state.copy(isLoading = true) } }
            .onEach {
            if (it.isNotEmpty()) {
                modifyState { state -> state.copy(
                    isSuccess = true,
                    tasks = it
                ) }
            } else {
                modifyState { state ->
                    state.copy(
                        isEmpty = true,
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchCompletedTasksByFolderUid() {
        val currentFolderUid = currentState.folderUid

        defaultFetchCompletedTasksByFolderUid.execute(currentFolderUid).onStart {
            modifyState { state -> state.copy(isCompleteLoading = true) }
        }.onEach {
            if (it.isNotEmpty()) {
                modifyState { state ->
                    state.copy(isCompleteSuccess = true,
                    isCompleteLoading = false,
                    completedTasks = it)
                }
            } else {
                modifyState { state ->
                    state.copy(
                        isCompleteLoading = false,
                        isCompleteEmpty = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
