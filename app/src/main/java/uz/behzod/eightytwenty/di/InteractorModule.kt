package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.domain.interactor.habit.*
import uz.behzod.eightytwenty.domain.interactor.habit_recommend.*
import uz.behzod.eightytwenty.domain.interactor.manager.ReadStorePermission
import uz.behzod.eightytwenty.domain.interactor.manager.ReadStorePermissionImpl
import uz.behzod.eightytwenty.domain.interactor.note.*
import uz.behzod.eightytwenty.domain.interactor.note_category.*
import uz.behzod.eightytwenty.domain.interactor.task.*
import uz.behzod.eightytwenty.domain.interactor.task_catalog.*
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

    @Binds
    @Singleton
    fun providesInsertHabitRecommend(
        interactor: InsertHabitRecommendImpl
    ): InsertHabitRecommend

    @Binds
    @Singleton
    fun providesUpdateHabitRecommend(
        interactor: UpdateHabitRecommendImpl
    ): UpdateHabitRecommend

    @Binds
    @Singleton
    fun providesDeleteHabitRecommend(
        interactor: DeleteHabitRecommendImpl
    ): DeleteHabitRecommend

    @Binds
    @Singleton
    fun providesFetchHabitRecommendsByCategory(
        interactor: FetchHabitRecommendsByCategoryImpl
    ): FetchHabitRecommendsByCategory

    @Binds
    @Singleton
    fun providesInsertHabitRecommends(
        interactor: InsertHabitRecommendsImpl
    ): InsertHabitRecommends

    @Binds
    @Singleton
    fun providesFetchHabitRecommendByUid(
        interactor: FetchHabitRecommendByUidImpl
    ): FetchHabitRecommendByUid

    /**
     * This part uses the declared interactors
     * associated with the task catalog.
     */

    @Binds
    @Singleton
    fun providesInsertTaskCatalog(
        interactor: InsertTaskCatalogImpl
    ): InsertTaskCatalog


    @Binds
    @Singleton
    fun providesUpdateTaskCatalog(
        interactor: UpdateTaskCatalogImpl
    ): UpdateTaskCatalog

    @Binds
    @Singleton
    fun providesDeleteTaskCatalog(
        interactor: DeleteTaskCatalogImpl
    ): DeleteTaskCatalog

    @Binds
    @Singleton
    fun providesIncrementTaskCount(
        interactor: IncrementTaskCountImpl
    ): IncrementTaskCount

    @Binds
    @Singleton
    fun providesDecrementTaskCount(
        interactor: DecrementTaskCountImpl
    ): DecrementTaskCountImpl

    @Binds
    @Singleton
    fun providesFetchTaskCatalogs(
        interactor: FetchTaskCatalogsImpl
    ): FetchTaskCatalogs

    @Binds
    @Singleton
    fun providesFetchTaskAndCatalogs(
        interactor: FetchTaskAndCatalogsImpl
    ): FetchTaskAndCatalogs

    @Binds
    @Singleton
    fun providesSearchTaskCatalog(
        interactor: SearchTaskCatalogImpl
    ): SearchTaskCatalog

    @Binds
    @Singleton
    fun providesInsertTask(
        interactor: InsertTaskImpl
    ): InsertTask

    @Binds
    @Singleton
    fun providesFetchTasks(
        interactor: FetchTasksImpl
    ): FetchTasks

    @Binds
    @Singleton
    fun providesSearchTasks(
        interactor: SearchTasksImpl
    ): SearchTasks

    @Binds
    @Singleton
    fun providesReadStorePermission(
        interactor: ReadStorePermissionImpl
    ): ReadStorePermission
}