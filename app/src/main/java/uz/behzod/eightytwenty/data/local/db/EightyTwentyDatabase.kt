package uz.behzod.eightytwenty.data.local.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import uz.behzod.eightytwenty.data.local.dao.HabitDao
import uz.behzod.eightytwenty.data.local.dao.HabitRecommendDao
import uz.behzod.eightytwenty.data.local.dao.NoteCategoryDao
import uz.behzod.eightytwenty.data.local.dao.NoteDao
import uz.behzod.eightytwenty.data.local.entities.HabitEntity
import uz.behzod.eightytwenty.data.local.entities.HabitRecommendEntity
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.worker.HabitRecommendWorker

@Database(
    entities = [
        NoteEntity::class, NoteCategoryEntity::class,
        HabitEntity::class, HabitRecommendEntity::class],
    version = 6,
    exportSchema = false
)
@TypeConverters(ZonedDateTimeConverter::class)
abstract class EightyTwentyDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
    abstract fun getNoteCategoryDao(): NoteCategoryDao
    abstract fun getHabitDao(): HabitDao
    abstract fun getHabitRecommendDao(): HabitRecommendDao

    companion object {
        @Volatile
        private var INSTANCE: EightyTwentyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            EightyTwentyDatabase::class.java,
            "eighty_database"
        ).createFromAsset("database/habit_recommend")
            .fallbackToDestructiveMigration()
            .build()

    }
}