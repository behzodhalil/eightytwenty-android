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

    @get:Binds
    @get:Singleton
    val FetchHabitsByDateImpl.bindHabitsByDate: FetchHabitsByDate

    @get:Binds
    @get:Singleton
    val InsertHabitRecommendImpl.bindInsertHabitRecommend: InsertHabitRecommend

    @get:Binds
    @get:Singleton
    val UpdateHabitRecommendImpl.bindUpdateHabitRecommend: UpdateHabitRecommend

    @get:Binds
    @get:Singleton
    val DeleteHabitRecommendImpl.bindDeleteHabitRecommend: DeleteHabitRecommend

    @get:Binds
    @get:Singleton
    val FetchHabitRecommendsByCategoryImpl.bindHabitRecommendsByCategory: FetchHabitRecommendsByCategory

    @get:Binds
    @get:Singleton
    val InsertHabitRecommendsImpl.bindInsertHabitRecommends: InsertHabitRecommends

    @get:Binds
    @get:Singleton
    val FetchHabitRecommendByUidImpl.bindHabitRecommendByUid: FetchHabitRecommendByUid

    @get:Binds
    @get:Singleton
    val InsertTaskCatalogImpl.bindInsertTaskCatalog: InsertTaskCatalog

    @get:Binds
    @get:Singleton
    val UpdateTaskCatalogImpl.bindUpdateTaskCatalog: UpdateTaskCatalog

    @get:Binds
    @get:Singleton
    val DeleteTaskCatalogImpl.bindDeleteTaskCatalog: DeleteTaskCatalog

    @get:Binds
    @get:Singleton
    val IncrementTaskCountImpl.bindIncrementTaskCount: IncrementTaskCount

    @get:Binds
    @get:Singleton
    val DecrementTaskCountImpl.bindDecrementTaskCount: DecrementTaskCount

    @get:Binds
    @get:Singleton
    val FetchTaskCatalogsImpl.bindTaskCatalogs: FetchTaskCatalogs

    @get:Binds
    @get:Singleton
    val FetchTaskAndCatalogsImpl.bindTaskAndCatalogs: FetchTaskAndCatalogs

    @get:Binds
    @get:Singleton
    val SearchTaskCatalogImpl.bindSearchTaskCatalog: SearchTaskCatalog

    @get:Binds
    @get:Singleton
    val InsertTaskImpl.bindInsertTask: InsertTask

    @get:Binds
    @get:Singleton
    val FetchTasksImpl.bindTasks: FetchTasks

    @get:Binds
    @get:Singleton
    val SearchTasksImpl.bindSearchTasks: SearchTasks

    @get:Binds
    @get:Singleton
    val ReadStorePermissionImpl.bindReadStorePermission: ReadStorePermission
}