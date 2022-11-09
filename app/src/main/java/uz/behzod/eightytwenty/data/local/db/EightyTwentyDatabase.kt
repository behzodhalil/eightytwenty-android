package uz.behzod.eightytwenty.data.local.db

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import uz.behzod.eightytwenty.data.local.dao.*
import uz.behzod.eightytwenty.data.local.entities.*
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

@Database(
    entities = [
        NoteEntity::class, NoteCategoryEntity::class,
        HabitEntity::class, HabitRecommendEntity::class,
        TaskEntity::class, TaskCatalogEntity::class,
        ScheduleEntity::class, AttachmentEntity::class,
        NoteImageEntity::class, UserEntity::class,
        WaterEntity::class, BillEntity::class,
        PillEntity::class],
    version = 20,
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
    abstract fun getWaterDao(): WaterDao
    abstract fun getPillDao(): PillDao
    abstract fun getBillDao(): BillDao
}

class SpecMigration : AutoMigrationSpec