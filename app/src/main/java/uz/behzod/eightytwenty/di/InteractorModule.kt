package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}