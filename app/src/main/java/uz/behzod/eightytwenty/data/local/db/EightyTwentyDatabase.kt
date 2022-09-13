package uz.behzod.eightytwenty.data.local.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import uz.behzod.eightytwenty.data.local.dao.*
import uz.behzod.eightytwenty.data.local.entities.*
import uz.behzod.eightytwenty.worker.HabitRecommendWorker

@Database(
    entities = [
        NoteEntity::class, NoteCategoryEntity::class,
        HabitEntity::class, HabitRecommendEntity::class,
        TaskEntity::class, TaskCatalogEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(
            from = 1, to = 2
        )
    ],
    exportSchema = true
)
@TypeConverters(ZonedDateTimeConverter::class)
abstract class EightyTwentyDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao
    abstract fun getNoteCategoryDao(): NoteCategoryDao
    abstract fun getHabitDao(): HabitDao
    abstract fun getHabitRecommendDao(): HabitRecommendDao
    abstract fun getTaskDao(): TaskDao
    abstract fun getTaskCatalogDao(): TaskCatalogDao

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
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                val request = OneTimeWorkRequestBuilder<HabitRecommendWorker>().build()
                WorkManager.getInstance(context).enqueue(request)
            }
        }).fallbackToDestructiveMigration().build()

    }
}


class SpecMigration : AutoMigrationSpec