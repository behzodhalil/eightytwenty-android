package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.domain.interactor.habit.*
import uz.behzod.eightytwenty.domain.interactor.note.*
import uz.behzod.eightytwenty.domain.interactor.note_category.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    @Singleton
    fun providesInsertNote(interactor: InsertNoteImpl): InsertNote

    @Binds
    @Singleton
    fun providesUpdateNote(interactor: UpdateNoteImpl): UpdateNote

    @Binds
    @Singleton
    fun providesDeleteNote(interactor: DeleteNoteImpl): DeleteNote

    @Binds
    @Singleton
    fun providesFetchTrashedNotes(interactor: FetchTrashedNotesImpl): FetchTrashedNotes

    @Binds
    @Singleton
    fun providesFetchAllNotes(interactor: FetchNotesImpl): FetchNotes

    @Binds
    @Singleton
    fun providesInsertNoteCategory(interactor: InsertNoteCategoryImpl): InsertNoteCategory

    @Binds
    @Singleton
    fun providesUpdateNoteCategory(interactor: UpdateNoteCategoryImpl): UpdateNoteCategory

    @Binds
    @Singleton
    fun providesDeleteNoteCategory(interactor: DeleteNoteCategoryImpl): DeleteNoteCategory

    @Binds
    @Singleton
    fun providesFetchAllCategories(interactor: FetchAllCategoriesImpl): FetchAllCategories

    @Binds
    @Singleton
    fun providesFetchAllCategoriesAndNotes(interactor: FetchAllCategoriesAndNotesImpl): FetchAllCategoriesAndNotes

    @Binds
    @Singleton
    fun providesFetchNotesByCategoryId(
        interactor: FetchNotesByCategoryIdImpl
    ): FetchNotesByCategoryId

    @Binds
    @Singleton
    fun providesFetchNoteById(
        interactor: FetchNoteByIdImpl
    ): FetchNoteById

    @Binds
    @Singleton
    fun providesSearchNotes(
        interactor: SearchNotesImpl
    ): SearchNotes

    @Binds
    @Singleton
    fun providesInsertHabit(
        interactor: InsertHabitImpl
    ): InsertHabit

    @Binds
    @Singleton
    fun providesUpdateHabit(
        interactor: UpdateHabitImpl
    ): UpdateHabit

    @Binds
    @Singleton
    fun providesDeleteHabit(
        interactor: DeleteHabitImpl
    ): DeleteHabit

    @Binds
    @Singleton
    fun providesFetchAllHabits(
        interactor: FetchAllHabitsImpl
    ): FetchAllHabits

    @Binds
    @Singleton
    fun providesFetchHabitByUid(
        interactor: FetchHabitByUidImpl
    ): FetchHabitByUid

    @Binds
    @Singleton
    fun providesFetchHabitsByDate(
        interactor: FetchHabitsByDateImpl
    ): FetchHabitsByDate
}