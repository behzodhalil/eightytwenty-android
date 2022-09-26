package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.AttachmentEntity

@Dao
interface AttachmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachment(attachment: AttachmentEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAttachment(attachment: AttachmentEntity)
    @Delete
    suspend fun deleteAttachment(attachment: AttachmentEntity)
    @Query("SELECT * FROM attachment_table WHERE attachment_uid =:attachmentUid")
    fun fetchAttachmentByUid(attachmentUid: Long): Flow<AttachmentEntity>
    @Query("SELECT * FROM attachment_table")
    fun fetchAttachments(): Flow<List<AttachmentEntity>>
}