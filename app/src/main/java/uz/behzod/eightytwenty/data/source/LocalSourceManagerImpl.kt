package uz.behzod.eightytwenty.data.source

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.dao.*
import uz.behzod.eightytwenty.data.local.entities.*
import javax.inject.Inject

class LocalSourceManagerImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteCategoryDao: NoteCategoryDao,
    private val habitDao: HabitDao,
    private val habitRecommendDao: HabitRecommendDao,
    private val taskDao: TaskDao,
    private val taskCatalogDao: TaskCatalogDao,
    private val scheduleDao: ScheduleDao
) : LocalSourceManager {

    override suspend fun insertNote(note: NoteEntity) {
        return noteDao.insert(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        return noteDao.update(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        return noteDao.delete(note)
    }

    override fun fetchTrashedNotes(): Flow<List<NoteEntity>> {
        return noteDao.fetchTrashedNotes()
    }

    override fun fetchAllNotes(): Flow<List<NoteEntity>> {
        return noteDao.fetchAllNotes()
    }

    override fun fetchNotesByCategoryId(categoryId: Long): Flow<List<NoteEntity>> {
        return noteDao.fetchNotesByCategoryId(categoryId)
    }

    override fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return noteDao.searchNote(query)
    }

    override fun fetchNoteById(noteId: Long): Flow<NoteEntity> {
        return noteDao.fetchNoteById(noteId)
    }

    override suspend fun insertNoteCategory(category: NoteCategoryEntity) {
        return noteCategoryDao.insert(category)
    }

    override suspend fun updateNoteCategory(category: NoteCategoryEntity) {
        return noteCategoryDao.update(category)
    }

    override suspend fun deleteNoteCategory(category: NoteCategoryEntity) {
        return noteCategoryDao.delete(category)
    }

    override suspend fun incrementNoteCount(noteCategoryId: Long) {
        return noteCategoryDao.incrementNoteCount(noteCategoryId)
    }

    override suspend fun decrementNoteCount(noteCategoryId: Long) {
        return noteCategoryDao.decrementNoteCount(noteCategoryId)
    }

    override fun fetchAllCategories(): Flow<List<NoteCategoryEntity>> {
        return noteCategoryDao.fetchAllCategories()
    }

    override suspend fun fetchIfCategoryIdExists(noteCategoryId: Int): Boolean {
        return noteCategoryDao.fetchIfCategoryIdExists(noteCategoryId)
    }

    override fun fetchAllCategoriesAndNotes(): Flow<List<CategoryAndNotes>> {
        return noteCategoryDao.fetchAllCategoriesWithNotes()
    }

    override suspend fun insertHabit(habit: HabitEntity): Long {
        return habitDao.insertHabit(habit)
    }

    override suspend fun updateHabit(habit: HabitEntity) {
        return habitDao.updateHabit(habit)
    }

    override suspend fun deleteHabit(habit: HabitEntity) {
        return habitDao.deleteHabit(habit)
    }

    override fun fetchHabitByUid(uid: Long): Flow<HabitEntity> {
        return habitDao.fetchHabitByUid(uid)
    }

    override fun fetchAllHabits(): Flow<List<HabitEntity>> {
        return habitDao.fetchAllHabits()
    }

    override fun fetchHabitsByDate(timestamp: String): Flow<List<HabitEntity>> {
        return habitDao.fetchHabitsByDate(timestamp)
    }

    // Habit Recommend: SourceManager functions

    override suspend fun insertHabitRecommend(habitRecommend: HabitRecommendEntity) {
        return habitRecommendDao.insertHabitRecommend(habitRecommend)
    }

    override suspend fun insertHabitRecommends(list: List<HabitRecommendEntity>) {
        return habitRecommendDao.insertHabitRecommends(list)
    }

    override suspend fun updateHabitRecommend(habitRecommend: HabitRecommendEntity) {
        return habitRecommendDao.updateHabitRecommend(habitRecommend)
    }

    override suspend fun deleteHabitRecommend(habitRecommend: HabitRecommendEntity) {
        return habitRecommendDao.deleteHabitRecommend(habitRecommend)
    }

    override fun fetchHabitRecommendsByCategory(category: String): Flow<List<HabitRecommendEntity>> {
        return habitRecommendDao.fetchHabitRecommendsByCategory(category)
    }

    override fun fetchHabitRecommendByUid(uid: Long): Flow<HabitRecommendEntity> {
        return habitRecommendDao.fetchHabitRecommendByUid(uid)
    }

    override suspend fun insertTask(task: TaskEntity): Long {
        return taskDao.insertTask(task)
    }

    override suspend fun updateTask(task: TaskEntity) {
        return taskDao.updateTask(task)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        return taskDao.deleteTask(task)
    }

    override fun fetchTaskByUid(uid: Long): Flow<List<TaskEntity>> {
        return taskDao.fetchTaskByUid(uid)
    }

    override fun fetchTasks(): Flow<List<TaskEntity>> {
        return taskDao.fetchTasks()
    }

    override suspend fun insertTaskCatalog(taskCatalog: TaskCatalogEntity) {
        return taskCatalogDao.insertTaskCatalog(taskCatalog)
    }

    override suspend fun updateTaskCatalog(taskCatalog: TaskCatalogEntity) {
        return taskCatalogDao.updateTaskCatalog(taskCatalog)
    }

    override suspend fun deleteTaskCatalog(taskCatalog: TaskCatalogEntity) {
        return taskCatalogDao.deleteTaskCatalog(taskCatalog)
    }

    override suspend fun incrementTaskCount(catalogUid: Long) {
        return taskCatalogDao.incrementTaskCount(catalogUid)
    }

    override suspend fun decrementTaskCount(catalogUid: Long) {
        return taskCatalogDao.decrementTaskCount(catalogUid)
    }

    override fun fetchTaskCatalogs(): Flow<List<TaskCatalogEntity>> {
        return taskCatalogDao.fetchTaskCatalogs()
    }

    override fun fetchTaskAndCatalogs(): Flow<List<CatalogAndTasks>> {
        return taskCatalogDao.fetchTaskAndCatalogs()
    }

    override suspend fun insertSchedule(schedule: ScheduleEntity) {
        return scheduleDao.insertSchedule(schedule)
    }

    override suspend fun updateSchedule(schedule: ScheduleEntity) {
        return scheduleDao.updateSchedule(schedule)
    }

    override suspend fun deleteByUid(taskUid: Long) {
        return scheduleDao.deleteByUid(taskUid)
    }

    override fun fetchSchedules(): Flow<List<ScheduleEntity>> {
        return scheduleDao.fetchSchedules()
    }

    override fun fetchSchedulesByUid(uid: Long): Flow<ScheduleEntity> {
        return scheduleDao.fetchSchedulesByUid(uid)
    }
}