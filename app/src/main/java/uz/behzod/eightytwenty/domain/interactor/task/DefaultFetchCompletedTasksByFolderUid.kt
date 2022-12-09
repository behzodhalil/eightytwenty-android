package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import javax.inject.Inject

class DefaultFetchCompletedTasksByFolderUid @Inject constructor(

): FetchCompletedTasksByFolderUid {

    override fun execute(folderUid: Long): Flow<List<TaskEntity>> {
        TODO("Not yet implemented")
    }
}
