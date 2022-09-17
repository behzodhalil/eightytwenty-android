package uz.behzod.eightytwenty.data.local.db

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import uz.behzod.eightytwenty.data.local.dao.*
import uz.behzod.eightytwenty.data.local.entities.*

@Database(
    entities = [
        NoteEntity::class, NoteCategoryEntity::class,
        HabitEntity::class, HabitRecommendEntity::class,
        TaskEntity::class, TaskCatalogEntity::class,
        ScheduleEntity::class],
    version = 28,
    autoMigrations = [
        AutoMigration(
            from = 27, to = 28, spec = SpecMigration::class
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
    abstract fun getScheduleDao(): ScheduleDao
}


class SpecMigration : AutoMigrationSpec