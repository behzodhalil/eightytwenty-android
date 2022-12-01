package uz.behzod.eightytwenty.data.local.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.di.CallbackScope
import javax.inject.Inject
import javax.inject.Provider

class NoteFolderCallback @Inject constructor(
    private val database: Provider<EightyTwentyDatabase>,
    @CallbackScope private val scope: CoroutineScope,
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val noteFolderDao = database.get().getNoteCategoryDao()

        scope.launch {
            noteFolderDao.insert(createNoteFolder())
        }
    }

}

private fun createNoteFolder(): NoteCategoryEntity {
    return NoteCategoryEntity(
        name = "Все заметки",
        count = 0
    )
}
