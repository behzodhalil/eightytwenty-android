package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Transaction
    @Query("SELECT * FROM note_table WHERE note_is_trashed = 1")
    suspend fun fetchTrashedNotes(): List<NoteEntity>

    @Transaction
    @Query("SELECT * FROM note_table WHERE note_is_trashed <> 1")
    fun fetchAllNotes(): Flow<List<NoteEntity>>
}