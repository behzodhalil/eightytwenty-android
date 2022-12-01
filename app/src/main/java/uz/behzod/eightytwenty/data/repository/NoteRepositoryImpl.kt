package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val sourceManager: LocalSourceManager
) : NoteRepository {


    override suspend fun insertNote(note: NoteEntity): Long {
        return sourceManager.insertNote(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        return sourceManager.updateNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        return sourceManager.deleteNote(note)
    }

    override fun fetchTrashedNotes(): Flow<List<NoteEntity>> {
        return sourceManager.fetchTrashedNotes()
    }

    override fun fetchAllNotes(): Flow<List<NoteEntity>> {
        return sourceManager.fetchAllNotes()
    }

    override fun fetchNotesByCategoryId(categoryId: Long): Flow<List<NoteRelation>> {
        return sourceManager.fetchNotesByCategoryId(categoryId)
    }

    override fun fetchNoteById(noteId: Long): Flow<NoteEntity> {
        return sourceManager.fetchNoteById(noteId)
    }

    override fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return sourceManager.searchNotes(query)
    }

    override fun fetchNoteRelationByUid(noteUid: Long): Flow<NoteRelation> {
        return sourceManager.fetchNoteRelationByUid(noteUid)
    }

    override fun fetchAllNoteRelation(): Flow<List<NoteRelation>> {
        return sourceManager.fetchAllNoteRelation()
    }
}
