package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.utils.Resource

interface NoteRepository {
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
}