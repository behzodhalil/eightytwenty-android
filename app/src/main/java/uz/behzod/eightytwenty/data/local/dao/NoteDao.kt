package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.data.local.entities.NoteFTS
import uz.behzod.eightytwenty.data.local.entities.NoteRelation

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Transaction
    @Query("SELECT * FROM note_table WHERE note_is_trashed = 1")
    fun fetchTrashedNotes(): Flow<List<NoteEntity>>

    @Transaction
    @Query("SELECT * FROM note_table WHERE note_is_trashed <> 1")
    fun fetchAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE note_category_id = :categoryId AND note_is_trashed <>1")
    fun fetchNotesByCategoryId(categoryId: Long): Flow<List<NoteRelation>>

    @Query("SELECT * FROM note_table WHERE note_id = :noteId AND note_is_trashed <> 1")
    fun fetchNoteById(noteId: Long): Flow<NoteEntity>

    @Query("SELECT * FROM note_table WHERE note_id = :noteUid AND note_is_trashed <> 1")
    fun fetchNoteRelationByUid(noteUid: Long): Flow<NoteRelation>

    @Query("SELECT * FROM note_table WHERE note_title LIKE :query")
    fun searchNote(query: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT *
        FROM note_table
        join note_fts_table on note_title = note_fts_title
        WHERE note_fts_table MATCH:query
        """
    )
    fun searchNoteFts(query: String): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE note_is_trashed <> 1")
    fun fetchAllNoteRelation(): Flow<List<NoteRelation>>
}
