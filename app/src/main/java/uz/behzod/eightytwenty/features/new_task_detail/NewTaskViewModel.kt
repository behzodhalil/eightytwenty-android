package uz.behzod.eightytwenty.features.new_task_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class NewTaskViewModel @Inject constructor(): ViewModel() {

    private var _task: MutableStateFlow<TaskEntity> = MutableStateFlow(value = TaskEntity())
    val task: StateFlow<TaskEntity> = _task.asStateFlow()

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