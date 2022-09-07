package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.CategoryAndNotes
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity

interface NoteCategoryRepository {
    suspend fun insertNoteCategory(category: NoteCategoryEntity)
    suspend fun updateNoteCategory(category: NoteCategoryEntity)
    suspend fun deleteNoteCategory(category: NoteCategoryEntity)
    suspend fun incrementNoteCount(noteCategoryId: Long)
    suspend fun decrementNoteCount(noteCategoryId: Long)
    fun fetchAllCategories(): Flow<List<NoteCategoryEntity>>
    fun fetchAllCategoriesAndNotes(): Flow<List<CategoryAndNotes>>
    suspend fun fetchIfCategoryIdExists(noteCategoryId: Int): Boolean
}