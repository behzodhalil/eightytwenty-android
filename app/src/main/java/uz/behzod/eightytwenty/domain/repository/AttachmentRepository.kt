package uz.behzod.eightytwenty.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.AttachmentEntity

interface AttachmentRepository {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachment(attachment: AttachmentEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAttachment(attachment: AttachmentEntity)
    suspend fun deleteAttachment(attachment: AttachmentEntity)
    fun fetchAttachmentByUid(attachmentUid: Long): Flow<AttachmentEntity>
    fun fetchAttachments(): Flow<List<AttachmentEntity>>
}