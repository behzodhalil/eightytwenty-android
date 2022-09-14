package uz.behzod.eightytwenty.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import uz.behzod.eightytwenty.data.local.callback.HabitRecommendCallback
import uz.behzod.eightytwenty.data.local.dao.*
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.data.source.LocalSourceManagerImpl
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(
        context: Application,
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
    fun providesLocalSourceManager(
        noteCategoryDao: NoteCategoryDao,
        noteDao: NoteDao,
        habitDao: HabitDao,
        habitRecommendDao: HabitRecommendDao,
        taskDao: TaskDao,
        taskCatalogDao: TaskCatalogDao
    ): LocalSourceManager {
        return LocalSourceManagerImpl(
            noteCategoryDao = noteCategoryDao,
            noteDao = noteDao,
            habitDao = habitDao,
            habitRecommendDao = habitRecommendDao,
            taskDao = taskDao,
            taskCatalogDao = taskCatalogDao
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


}