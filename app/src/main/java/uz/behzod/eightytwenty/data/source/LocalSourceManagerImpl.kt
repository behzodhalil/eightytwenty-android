package uz.behzod.eightytwenty.data.source

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.dao.*
import uz.behzod.eightytwenty.data.local.entities.*
import uz.behzod.eightytwenty.utils.extension.printDebug
import javax.inject.Inject

class LocalSourceManagerImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteCategoryDao: NoteCategoryDao,
    private val habitDao: HabitDao,
    private val habitRecommendDao: HabitRecommendDao,
    private val taskDao: TaskDao,
    private val taskCatalogDao: TaskCatalogDao,
    private val scheduleDao: ScheduleDao,
    private val attachmentDao: AttachmentDao,
    private val imagesDao: NoteImageDao,
    private val userDao: UserDao
) : LocalSourceManager {

    override suspend fun insertNote(note: NoteEntity): Long {
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

    override fun fetchNoteRelationByUid(noteUid: Long): Flow<NoteRelation> {
        return noteDao.fetchNoteRelationByUid(noteUid)
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

    override fun fetchAllNoteRelation(): Flow<List<NoteRelation>> {
        return noteDao.fetchAllNoteRelation()
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

    override fun searchCatalog(catalogName: String): Flow<List<TaskCatalogEntity>> {
        return taskCatalogDao.searchCatalog(catalogName)
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

    override fun fetchTaskAndSchedule(): Flow<List<TaskAndSchedule>> {
        return taskDao.fetchTaskAndSchedule()
    }

    override fun searchTasks(taskName: String): Flow<List<TaskEntity>> {
        return taskDao.searchTasks(taskName)
    }

    override fun fetchTasksRecent(): Flow<List<TaskEntity>> {
        return taskDao.fetchTasksRecent()
    }

    override fun fetchTasksNearTime(): Flow<List<TaskEntity>> {
        return taskDao.fetchTasksNearTime()
    }

    override fun fetchLimitedTasks(): Flow<List<TaskEntity>> {
        return taskDao.fetchLimitedTasks()
    }

    override suspend fun insertAttachment(attachment: AttachmentEntity) {
        return attachmentDao.insertAttachment(attachment)
    }

    override suspend fun updateAttachment(attachment: AttachmentEntity) {
        return attachmentDao.updateAttachment(attachment)
    }

    override suspend fun deleteAttachment(attachment: AttachmentEntity) {
        return attachmentDao.deleteAttachment(attachment)
    }

    override fun fetchAttachmentByUid(attachmentUid: Long): Flow<AttachmentEntity> {
        return attachmentDao.fetchAttachmentByUid(attachmentUid)
    }

    override fun fetchAttachments(): Flow<List<AttachmentEntity>> {
        return attachmentDao.fetchAttachments()
    }

    override suspend fun insertNoteImage(image: NoteImageEntity) {
        printDebug { "SourceManager: Images are $image" }
        return imagesDao.insertNoteImage(image)
    }

    override suspend fun updateNoteImage(image: NoteImageEntity) {
        return imagesDao.updateNoteImage(image)
    }

    override suspend fun deleteNoteImage(image: NoteImageEntity) {
        return imagesDao.deleteNoteImage(image)
    }

    override fun fetchImageByNoteUid(uuid: Long): Flow<NoteImageEntity> {
        return imagesDao.fetchImageByNoteUid(uuid)
    }

    override fun fetchNoteImages(): Flow<List<NoteImageEntity>> {
        return imagesDao.fetchNoteImages()
    }

    override suspend fun insertNoteImages(images: List<NoteImageEntity>) {
        return imagesDao.insertNoteImages(images)
    }

    override fun fetchImagesByNoteByNoteUid(uuid: Long): Flow<List<NoteImageEntity>> {
        return imagesDao.fetchImagesByNoteByNoteUid(uuid)
    }

    override suspend fun insertUser(user: UserEntity) {
        return userDao.insertUser(user)
    }

    override suspend fun updateUser(user: UserEntity) {
        return userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: UserEntity) {
        return userDao.deleteUser(user)
    }

    override fun fetchUser(): Flow<UserEntity> {
        return userDao.fetchUser()
    }
}