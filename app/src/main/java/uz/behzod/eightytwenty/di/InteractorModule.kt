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

    @get: Binds
    @get: Singleton
    val InsertNoteImpl.bindInsertNote: InsertNote

    @get: Binds
    @get: Singleton
    val UpdateNoteImpl.bindUpdateNote: UpdateNote

    @get: Binds
    @get: Singleton
    val DeleteNoteImpl.bindDeleteNote: DeleteNote

    @get: Binds
    @get: Singleton
    val FetchTrashedNotesImpl.bindTrashedNotes: FetchTrashedNotes

    @get:Binds
    @get: Singleton
    val FetchNotesImpl.bindFetchNotes: FetchNotes

    @get: Binds
    @get: Singleton
    val InsertNoteCategoryImpl.bindInsertNoteCategory: InsertNoteCategory

    @get: Binds
    @get: Singleton
    val UpdateNoteCategoryImpl.bindUpdateNoteCategory: UpdateNoteCategory

    @get: Binds
    @get: Singleton
    val DeleteNoteCategoryImpl.bindDeleteNoteCategory: DeleteNoteCategory

    @get: Binds
    @get: Singleton
    val FetchAllCategoriesImpl.bindAllCategories: FetchAllCategories

    @get: Binds
    @get: Singleton
    val FetchAllCategoriesAndNotesImpl.bindFetchAllCategoriesAndNotes: FetchAllCategoriesAndNotes

    @get: Binds
    @get: Singleton
    val FetchNotesByCategoryIdImpl.bindFetchNotesByCategoryId: FetchNotesByCategoryId

    @get: Binds
    @get: Singleton
    val FetchNoteByIdImpl.bindFetchNoteById: FetchNoteById

    @get: Binds
    @get: Singleton
    val SearchNotesImpl.bindSearchNotes: SearchNotes

    @get: Binds
    @get: Singleton
    val InsertHabitImpl.bindInsertHabit: InsertHabit

    @get: Binds
    @get: Singleton
    val UpdateHabitImpl.bindUpdateHabit: UpdateHabit

    @get: Binds
    @get: Singleton
    val DeleteHabitImpl.bindDeleteHabit: DeleteHabit

    @get: Binds
    @get: Singleton
    val FetchAllHabitsImpl.bindFetchAllHabits: FetchAllHabits

    @get: Binds
    @get: Singleton
    val FetchHabitByUidImpl.bindFetchHabitByUid: FetchHabitByUid

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