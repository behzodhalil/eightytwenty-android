package uz.behzod.eightytwenty.data.local.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.di.CallbackScope
import javax.inject.Inject
import javax.inject.Provider

class TaskFolderCallback @Inject constructor(
    private val datababe: Provider<EightyTwentyDatabase>,
    @CallbackScope private val scope: CoroutineScope
): RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val taskDao = datababe.get().getTaskCatalogDao()

        scope.launch {

            taskDao.insertTaskCatalog(createTaskFolder())
        }
    }
}

private fun createTaskFolder(): TaskCatalogEntity {
    return TaskCatalogEntity(
        name = "Все заметки",
        taskCount = 0L,
        taskId = 0L
    )
}
