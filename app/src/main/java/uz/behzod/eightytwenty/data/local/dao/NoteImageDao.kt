package uz.behzod.eightytwenty.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

@Dao
interface NoteImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteImage(image: NoteImageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteImages(images: List<NoteImageEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNoteImage(image: NoteImageEntity)

    @Delete
    suspend fun deleteNoteImage(image: NoteImageEntity)

    @Query("SELECT * FROM note_image_table WHERE note_uid =:uuid")
    fun fetchImageByNoteUid(uuid: Long): Flow<NoteImageEntity>

    @Query("SELECT * FROM note_image_table WHERE note_uid =:uuid")
    fun fetchImagesByNoteByNoteUid(uuid: Long): Flow<List<NoteImageEntity>>

    @Query("SELECT * FROM note_image_table")
    fun fetchNoteImages(): Flow<List<NoteImageEntity>>
}