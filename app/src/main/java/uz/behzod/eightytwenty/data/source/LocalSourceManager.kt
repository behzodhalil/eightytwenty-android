package uz.behzod.eightytwenty.data.source

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.*

interface LocalSourceManager {
    // Note: Dao functions

    suspend fun insertNote(note: NoteEntity): Long
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
    fun fetchTrashedNotes(): Flow<List<NoteEntity>>
    fun fetchAllNotes(): Flow<List<NoteEntity>>
    fun fetchNotesByCategoryId(categoryId: Long): Flow<List<NoteEntity>>
    fun fetchNoteById(noteId: Long): Flow<NoteEntity>
    fun searchNotes(query: String): Flow<List<NoteEntity>>

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachment(attachment: AttachmentEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAttachment(attachment: AttachmentEntity)
    suspend fun deleteAttachment(attachment: AttachmentEntity)
    fun fetchAttachmentByUid(attachmentUid: Long): Flow<AttachmentEntity>
    fun fetchAttachments(): Flow<List<AttachmentEntity>>
}