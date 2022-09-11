package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.data.repository.HabitRecommendRepositoryImpl
import uz.behzod.eightytwenty.data.repository.HabitRepositoryImpl
import uz.behzod.eightytwenty.data.repository.NoteCategoryRepositoryImpl
import uz.behzod.eightytwenty.data.repository.NoteRepositoryImpl
import uz.behzod.eightytwenty.domain.repository.HabitRecommendRepository
import uz.behzod.eightytwenty.domain.repository.HabitRepository
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun providesNoteRepository(
        repo: NoteRepositoryImpl
    ): NoteRepository

    @Binds
    @Singleton
    fun providesNoteCategoryRepository(
        repo: NoteCategoryRepositoryImpl
    ): NoteCategoryRepository

    @Binds
    @Singleton
    fun providesHabitRepository(
        repository: HabitRepositoryImpl
    ): HabitRepository

    @Binds
    @Singleton
    fun providesHabitRecommendRepository(
        repository: HabitRecommendRepositoryImpl
    ): HabitRecommendRepository
}