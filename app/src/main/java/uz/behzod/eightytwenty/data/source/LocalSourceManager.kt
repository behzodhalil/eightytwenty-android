package uz.behzod.eightytwenty.data.source

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.CategoryAndNotes
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.data.local.entities.NoteEntity

interface LocalSourceManager {
    suspend fun insertNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
    fun fetchTrashedNotes(): Flow<List<NoteEntity>>
    fun fetchAllNotes(): Flow<List<NoteEntity>>
    fun fetchNotesByCategoryId(categoryId: Long): Flow<List<NoteEntity>>

    suspend fun insertNoteCategory(category: NoteCategoryEntity)
    suspend fun updateNoteCategory(category: NoteCategoryEntity)
    suspend fun deleteNoteCategory(category: NoteCategoryEntity)
    suspend fun incrementNoteCount(noteCategoryId: Long)
    suspend fun decrementNoteCount(noteCategoryId: Long)
    fun fetchAllCategories(): Flow<List<NoteCategoryEntity>>
    fun fetchAllCategoriesAndNotes(): Flow<List<CategoryAndNotes>>
    suspend fun fetchIfCategoryIdExists(noteCategoryId: Int): Boolean

}