package uz.behzod.eightytwenty.data.source

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.*
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

interface LocalSourceManager {
    // Note: Dao functions

    suspend fun insertNote(note: NoteEntity): Long
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
    fun fetchTrashedNotes(): Flow<List<NoteEntity>>
    fun fetchAllNotes(): Flow<List<NoteEntity>>
    fun fetchNotesByCategoryId(categoryId: Long): Flow<List<NoteEntity>>
    fun fetchNoteById(noteId: Long): Flow<NoteEntity>
    fun fetchNoteRelationByUid(noteUid: Long): Flow<NoteRelation>
    fun searchNotes(query: String): Flow<List<NoteEntity>>
    fun fetchAllNoteRelation(): Flow<List<NoteRelation>>

    // NoteCategory: Dao functions
    suspend fun insertNoteCategory(category: NoteCategoryEntity)
    suspend fun updateNoteCategory(category: NoteCategoryEntity)
    suspend fun deleteNoteCategory(category: NoteCategoryEntity)
    suspend fun incrementNoteCount(noteCategoryId: Long)
    suspend fun decrementNoteCount(noteCategoryId: Long)
    fun fetchAllCategories(): Flow<List<NoteCategoryEntity>>
    fun fetchAllCategoriesAndNotes(): Flow<List<CategoryAndNotes>>
    suspend fun fetchIfCategoryIdExists(noteCategoryId: Int): Boolean

    // Habit: Dao functions

    suspend fun insertHabit(habit: HabitEntity):Long
    suspend fun updateHabit(habit: HabitEntity)
    suspend fun deleteHabit(habit: HabitEntity)
    fun fetchHabitByUid(uid: Long): Flow<HabitEntity>
    fun fetchAllHabits(): Flow<List<HabitEntity>>
    fun fetchHabitsByDate(timestamp: String): Flow<List<HabitEntity>>

    // HabitRecommend: Dao functions

    suspend fun insertHabitRecommend(habitRecommend: HabitRecommendEntity)
    suspend fun insertHabitRecommends(list: List<HabitRecommendEntity>)
    suspend fun updateHabitRecommend(habitRecommend: HabitRecommendEntity)
    suspend fun deleteHabitRecommend(habitRecommend: HabitRecommendEntity)
    fun fetchHabitRecommendsByCategory(category: String):Flow<List<HabitRecommendEntity>>
    fun fetchHabitRecommendByUid(uid: Long): Flow<HabitRecommendEntity>

    // Task: Dao functions

    suspend fun insertTask(task: TaskEntity): Long
    suspend fun updateTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
    fun fetchTaskByUid(uid: Long): Flow<List<TaskEntity>>
    fun fetchTasks(): Flow<List<TaskEntity>>
    fun fetchTaskAndSchedule(): Flow<List<TaskAndSchedule>>
    fun searchTasks(taskName: String): Flow<List<TaskEntity>>
    fun fetchTasksRecent(): Flow<List<TaskEntity>>
    fun fetchTasksNearTime(): Flow<List<TaskEntity>>
    fun fetchLimitedTasks(): Flow<List<TaskEntity>>

    // TaskCatalog: Dao functions

    suspend fun insertTaskCatalog(taskCatalog: TaskCatalogEntity)
    suspend fun updateTaskCatalog(taskCatalog: TaskCatalogEntity)
    suspend fun deleteTaskCatalog(taskCatalog: TaskCatalogEntity)
    suspend fun incrementTaskCount(catalogUid: Long)
    suspend fun decrementTaskCount(catalogUid: Long)
    fun fetchTaskCatalogs(): Flow<List<TaskCatalogEntity>>
    fun fetchTaskAndCatalogs(): Flow<List<CatalogAndTasks>>
    fun searchCatalog(catalogName: String): Flow<List<TaskCatalogEntity>>

    // Schedule: Dao functions

    suspend fun insertSchedule(schedule: ScheduleEntity)
    suspend fun updateSchedule(schedule: ScheduleEntity)
    suspend fun deleteByUid(taskUid: Long)
    fun fetchSchedules(): Flow<List<ScheduleEntity>>
    fun fetchSchedulesByUid(uid: Long): Flow<ScheduleEntity>

    // Attachment: Dao functions

    suspend fun insertAttachment(attachment: AttachmentEntity)
    suspend fun updateAttachment(attachment: AttachmentEntity)
    suspend fun deleteAttachment(attachment: AttachmentEntity)
    fun fetchAttachmentByUid(attachmentUid: Long): Flow<AttachmentEntity>
    fun fetchAttachments(): Flow<List<AttachmentEntity>>

    // Note Image: Dao functions

    suspend fun insertNoteImage(image: NoteImageEntity)
    suspend fun insertNoteImages(images: List<NoteImageEntity>)
    suspend fun updateNoteImage(image: NoteImageEntity)
    suspend fun deleteNoteImage(image: NoteImageEntity)
    fun fetchImageByNoteUid(uuid: Long): Flow<NoteImageEntity>
    fun fetchImagesByNoteByNoteUid(uuid: Long): Flow<List<NoteImageEntity>>
    fun fetchNoteImages(): Flow<List<NoteImageEntity>>

    // User: Dao functions

    suspend fun insertUser(user: UserEntity)
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
    fun fetchUser(): Flow<UserEntity>

    /**
     * The DAO functions of WATER.
     */
    suspend fun insertWater(water: WaterEntity): Long
    suspend fun updateWater(water: WaterEntity)
    fun fetchWaterAfterTimestamp(timestamp: Long): Flow<List<WaterEntity>>
    fun fetchWaters(): Flow<List<WaterEntity>>


    /**
     * The DAO functions of PILL.
      */
    suspend fun insertPill(pill: PillEntity)
    suspend fun updatePill(pill: PillEntity)
    suspend fun deletePill(pill: PillEntity)
    fun fetchPills(): Flow<List<PillEntity>>

    /**
     * The DAO functions of BILL.
     */
    suspend fun insertBill(bill: BillEntity):Long
    suspend fun updateBill(bill: BillEntity)
    suspend fun deleteBill(bill: BillEntity)
    fun fetchBills(): Flow<List<BillEntity>>

}