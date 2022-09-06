package uz.behzod.eightytwenty.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.data.local.dao.NoteCategoryDao
import uz.behzod.eightytwenty.data.local.dao.NoteDao
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.source.LocalSourceManager
import uz.behzod.eightytwenty.data.local.source.LocalSourceManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): EightyTwentyDatabase {
        return EightyTwentyDatabase.invoke(context)
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
    fun providesLocalSourceManager(
        noteCategoryDao: NoteCategoryDao,
        noteDao: NoteDao
    ): LocalSourceManager {
        return LocalSourceManagerImpl(noteCategoryDao = noteCategoryDao, noteDao = noteDao)
    }
}