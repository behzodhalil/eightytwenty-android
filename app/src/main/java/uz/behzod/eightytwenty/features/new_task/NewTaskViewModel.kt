package uz.behzod.eightytwenty.features.new_task

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.interactor.task.InsertTask
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.debugger
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(
    private val iInsertTask: InsertTask
) : ViewModel() {

    private var _task: MutableStateFlow<TaskEntity> = MutableStateFlow(value = TaskEntity())
    val task: StateFlow<TaskEntity> = _task.asStateFlow()

    private var _endDate: MutableLiveData<Int> = MutableLiveData(0)
    val endDate = _endDate

    private var _uiState: MutableStateFlow<NewTaskUiState> =
        MutableStateFlow(NewTaskUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun insertTaskWithScheduleAndNote(
        task: TaskEntity,
        notes: List<NoteDomainModel>,
        schedules: List<ScheduleEntity>
    ) {
        viewModelScope.launch {
            _uiState.value = NewTaskUiState.Loading
            try {
                iInsertTask.invoke(
                    task = task,
                    noteList = notes,
                    scheduleList = schedules
                )
                _uiState.value = NewTaskUiState.Success(
                    task,schedules,notes
                )
            } catch (e: Exception) {
                _uiState.value = NewTaskUiState.Empty
                debugger { "Message is $e" }
            }

        }
    }

    fun setEndDate(item: Int) {
        _endDate.value = item
    }

    fun setTask(task: TaskEntity?) {
        task?.let {
            _task.value = it
        }
    }

    fun getTask(): TaskEntity? {
        return task.value
    }

    fun setEndDate(endDate: ZonedDateTime?) {
        val task = getTask()
        task?.let {
            it.endDate = endDate
            setTask(task)
        }
    }

}