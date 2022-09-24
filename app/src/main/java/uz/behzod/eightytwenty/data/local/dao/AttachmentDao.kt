package uz.behzod.eightytwenty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.AttachmentEntity

@Dao
interface AttachmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachment(attachment: AttachmentEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAttachment(attachment: AttachmentEntity)
    suspend fun deleteAttachment(attachment: AttachmentEntity)
    fun fetchAttachmentByUid(attachment: AttachmentEntity)
    fun fetchAttachment(): Flow<List<AttachmentEntity>>
}