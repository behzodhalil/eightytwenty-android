package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.CategoryAndNotes
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import javax.inject.Inject

class NoteCategoryRepositoryImpl @Inject constructor(
    private val sourceManager: LocalSourceManager
): NoteCategoryRepository {

    override suspend fun insertNoteCategory(category: NoteCategoryEntity) {
        return sourceManager.insertNoteCategory(category)
    }

    override suspend fun updateNoteCategory(category: NoteCategoryEntity) {
        return sourceManager.updateNoteCategory(category)
    }

    override suspend fun deleteNoteCategory(category: NoteCategoryEntity) {
        return sourceManager.deleteNoteCategory(category)
    }

    override suspend fun incrementNoteCount(noteCategoryId: Long) {
        return sourceManager.incrementNoteCount(noteCategoryId)
    }

    override suspend fun decrementNoteCount(noteCategoryId: Long) {
        return sourceManager.decrementNoteCount(noteCategoryId)
    }

    override fun fetchAllCategories(): Flow<List<NoteCategoryEntity>> {
        return sourceManager.fetchAllCategories()
    }

    override suspend fun fetchIfCategoryIdExists(noteCategoryId: Int): Boolean {
        return sourceManager.fetchIfCategoryIdExists(noteCategoryId)
    }

    override fun fetchAllCategoriesAndNotes(): Flow<List<CategoryAndNotes>> {
        return sourceManager.fetchAllCategoriesAndNotes()
    }
}