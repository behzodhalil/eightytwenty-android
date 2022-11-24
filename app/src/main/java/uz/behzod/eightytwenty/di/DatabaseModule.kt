package uz.behzod.eightytwenty.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.data.local.callback.HabitRecommendCallback
import uz.behzod.eightytwenty.data.local.dao.*
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.data.source.LocalSourceManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(
        @ApplicationContext context: Application,
        callback: HabitRecommendCallback
    ): EightyTwentyDatabase {
        return Room.databaseBuilder(context, EightyTwentyDatabase::class.java, "eighty_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    @Singleton
    fun providesNoteDao(database: EightyTwentyDatabase): NoteDao {
        return database.getNoteDao()
    }

    @Provides
    @Singleton
    fun providesNoteCategoryDao(database: EightyTwentyDatabase): NoteCategoryDao {
        return database.getNoteCategoryDao()
    }

    @Provides
    @Singleton
    fun providesHabitDao(database: EightyTwentyDatabase): HabitDao {
        return database.getHabitDao()
    }

    @Provides
    @Singleton
    fun providesHabitRecommendDao(database: EightyTwentyDatabase): HabitRecommendDao {
        return database.getHabitRecommendDao()
    }

    @Provides
    @Singleton
    fun providesImagesDao(database: EightyTwentyDatabase): NoteImageDao {
        return database.getNoteImageDao()
    }

    @Provides
    @Singleton
    fun providesLocalSourceManager(
        noteCategoryDao: NoteCategoryDao,
        noteDao: NoteDao,
        habitDao: HabitDao,
        habitRecommendDao: HabitRecommendDao,
        taskDao: TaskDao,
        taskCatalogDao: TaskCatalogDao,
        scheduleDao: ScheduleDao,
        attachmentDao: AttachmentDao,
        imageDao: NoteImageDao,
        userDao: UserDao,
        waterDao: WaterDao,
        billDao: BillDao,
        pillDao: PillDao
    ): LocalSourceManager {
        return LocalSourceManagerImpl(
            noteCategoryDao = noteCategoryDao,
            noteDao = noteDao,
            habitDao = habitDao,
            habitRecommendDao = habitRecommendDao,
            taskDao = taskDao,
            taskCatalogDao = taskCatalogDao,
            scheduleDao = scheduleDao,
            attachmentDao = attachmentDao,
            imagesDao = imageDao,
            userDao = userDao,
            waterDao = waterDao,
            billDao = billDao,
            pillDao = pillDao
        )
    }

    @Provides
    @Singleton
    fun providesTaskDao(
        database: EightyTwentyDatabase
    ): TaskDao {
        return database.getTaskDao()
    }

    @Provides
    @Singleton
    fun providesTaskCatalogDao(
        database: EightyTwentyDatabase
    ): TaskCatalogDao {
        return database.getTaskCatalogDao()
    }

    @Provides
    @Singleton
    fun providesScheduleDao(
        database: EightyTwentyDatabase
    ): ScheduleDao {
        return database.getScheduleDao()
    }

    @Provides
    @Singleton
    fun providesAttachmentDao(
        database: EightyTwentyDatabase
    ): AttachmentDao {
        return database.getAttachmentDao()
    }

    @Provides
    @Singleton
    fun providesUserDao(
        database: EightyTwentyDatabase
    ): UserDao {
        return database.getUserDao()
    }
    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesWaterDao(
        db: EightyTwentyDatabase
    ): WaterDao {
        return db.getWaterDao()
    }

    @Provides
    @Singleton
    fun providesBillDao(
        db: EightyTwentyDatabase
    ): BillDao {
        return db.getBillDao()
    }

    @Provides
    @Singleton
    fun providesPillDao(
        db: EightyTwentyDatabase
    ): PillDao {
        return db.getPillDao()
    }

}
