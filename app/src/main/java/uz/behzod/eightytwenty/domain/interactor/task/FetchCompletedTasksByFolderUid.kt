package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.TaskEntity

interface FetchCompletedTasksByFolderUid {
    fun execute(folderUid: Long): Flow<List<TaskEntity>>
}
