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
        ScheduleEntity::class, AttachmentEntity::class,
        NoteImageEntity::class, UserEntity::class],
    version = 8,
    exportSchema = true
)

@TypeConverters(ZonedDateTimeConverter::class, UriConverter::class)
abstract class EightyTwentyDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getNoteCategoryDao(): NoteCategoryDao
    abstract fun getHabitDao(): HabitDao
    abstract fun getHabitRecommendDao(): HabitRecommendDao
    abstract fun getTaskDao(): TaskDao
    abstract fun getTaskCatalogDao(): TaskCatalogDao
    abstract fun getScheduleDao(): ScheduleDao
    abstract fun getAttachmentDao(): AttachmentDao
    abstract fun getNoteImageDao(): NoteImageDao
    abstract fun getUserDao(): UserDao
}

class SpecMigration : AutoMigrationSpec