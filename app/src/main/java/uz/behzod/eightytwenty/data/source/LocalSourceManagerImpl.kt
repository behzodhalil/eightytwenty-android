package uz.behzod.eightytwenty.data.source

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.dao.NoteCategoryDao
import uz.behzod.eightytwenty.data.local.dao.NoteDao
import uz.behzod.eightytwenty.data.local.entities.CategoryAndNotes
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import javax.inject.Inject

class LocalSourceManagerImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteCategoryDao: NoteCategoryDao
): LocalSourceManager {

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
}