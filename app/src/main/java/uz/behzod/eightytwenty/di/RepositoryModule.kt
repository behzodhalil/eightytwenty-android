package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.data.repository.NoteCategoryRepositoryImpl
import uz.behzod.eightytwenty.data.repository.NoteRepositoryImpl
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.domain.repository.NoteRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun providesNoteRepository(
        repo: NoteRepositoryImpl
    ): NoteRepository

    @Binds
    fun providesNoteCategoryRepository(
        repo: NoteCategoryRepositoryImpl
    ): NoteCategoryRepository
}