package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.domain.interactor.bill.*
import uz.behzod.eightytwenty.domain.interactor.habit.*
import uz.behzod.eightytwenty.domain.interactor.habit_recommend.*
import uz.behzod.eightytwenty.domain.interactor.image.*
import uz.behzod.eightytwenty.domain.interactor.login.*
import uz.behzod.eightytwenty.domain.interactor.manager.ReadStorePermission
import uz.behzod.eightytwenty.domain.interactor.manager.ReadStorePermissionImpl
import uz.behzod.eightytwenty.domain.interactor.note.*
import uz.behzod.eightytwenty.domain.interactor.note_category.*
import uz.behzod.eightytwenty.domain.interactor.pill.*
import uz.behzod.eightytwenty.domain.interactor.task.*
import uz.behzod.eightytwenty.domain.interactor.task_catalog.*
import uz.behzod.eightytwenty.domain.interactor.water.*
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

    @get: Binds
    @get: Singleton
    val DeleteNoteImageImpl.bindDeleteNoteImage: DeleteNoteImage

    @get: Binds
    @get: Singleton
    val FetchImageByNoteUidImpl.bindImageByNoteUid: FetchImageByNoteUid

    @get: Binds
    @get: Singleton
    val FetchImagesByNoteByNoteUidImpl.bindImagesByNoteUid: FetchImagesByNoteByNoteUid

    @get: Binds
    @get: Singleton
    val FetchNoteImagesImpl.bindNoteImages: FetchNoteImages

    @get: Binds
    @get: Singleton
    val InsertNoteImageImpl.bindInsertNoteImage: InsertNoteImage

    @get: Binds
    @get: Singleton
    val InsertNoteImagesImpl.bindInsertNoteImages: InsertNoteImages

    @get: Binds
    @get: Singleton
    val UpdateNoteImageImpl.bindUpdateNoteImage: UpdateNoteImage

    @get: Binds
    @get: Singleton
    val DefaultFetchNoteRelationByUid.bindNoteRelationByUid: FetchNoteRelationByUid

    @get:Binds
    @get:Singleton
    val DefaultMoveToGroupNote.bindMoveToGroupNote: MoveToGroupNote

    @get:Binds
    @get:Singleton
    val DefaultFetchAllNoteRelation.bindAllNoteRelation:FetchAllNoteRelation

    @get:Binds
    @get:Singleton
    val DefaultCreateUserWithEmailAndPassword.bindCreateUser: CreateUserWithEmailAndPassword

    @get:Binds
    @get:Singleton
    val DefaultSignInEmailWithPassword.bindSignIn: SignInEmailWithPassword

    @get:Binds
    @get:Singleton
    val DefaultInsertUser.bindInsertUser: InsertUser

    @get:Binds
    @get:Singleton
    val DefaultUpdateUser.bindUpdateUser: UpdateUser

    @get:Binds
    @get:Singleton
    val DefaultDeleteUser.bindDeleteUser: DeleteUser

    @get:Binds
    @get:Singleton
    val DefaultFetchUser.bindUser: FetchUser

    @get:Binds
    @get:Singleton
    val DefaultFetchTasksNearTime.bindTasksNearTime: FetchTasksNearTime

    @get:Binds
    @get:Singleton
    val DefaultFetchTasksRecent.bindTasksRecent: FetchTasksRecent

    @get:Binds
    @get:Singleton
    val DefaultFetchLimitedTasks.bindTasksLimited: FetchLimitedTasks

    @get:Binds
    @get:Singleton
    val DefaultInsertWater.bindInsertWater: InsertWater

    @get:Binds
    @get:Singleton
    val DefaultUpdateWater.bindUpdateWater: UpdateWater

    @get:Binds
    @get:Singleton
    val DefaultDeleteWater.bindDeleteWater: DeleteWater

    @get:Binds
    @get:Singleton
    val DefaultFetchWaters.bindWaters: FetchWaters

    @get:Binds
    @get:Singleton
    val DefaultFetchWaterAfterTimestamp.bindWaterAfterTimestamp: FetchWaterAfterTimestamp

    @get:Binds
    @get:Singleton
    val DefaultInsertPill.bindInsertPill: InsertPill

    @get:Binds
    @get:Singleton
    val DefaultUpdatePill.bindUpdatePill: UpdatePill

    @get:Binds
    @get:Singleton
    val DefaultDeletePill.bindDeletePill: DeletePill

    @get:Binds
    @get:Singleton
    val DefaultFetchPills.bindPills: FetchPills

    @get:Binds
    @get:Singleton
    val DefaultInsertBill.bindInsertBill: InsertBill

    @get:Binds
    @get:Singleton
    val DefaultUpdateBill.bindUpdateBill: UpdateBill

    @get:Binds
    @get:Singleton
    val DefaultDeleteBill.bindDeleteBill: DeleteBill

    @get:Binds
    @get:Singleton
    val DefaultFetchBills.bindBills: FetchBills
}