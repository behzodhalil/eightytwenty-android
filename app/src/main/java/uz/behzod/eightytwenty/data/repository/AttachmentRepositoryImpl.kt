package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.AttachmentEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.AttachmentRepository
import javax.inject.Inject

class AttachmentRepositoryImpl @Inject constructor(
    private val sourceManager: LocalSourceManager
): AttachmentRepository {

    override suspend fun insertAttachment(attachment: AttachmentEntity) {
        return sourceManager.insertAttachment(attachment)
    }

    override suspend fun updateAttachment(attachment: AttachmentEntity) {
        return sourceManager.updateAttachment(attachment)
    }

    override suspend fun deleteAttachment(attachment: AttachmentEntity) {
        return sourceManager.deleteAttachment(attachment)
    }

    override fun fetchAttachmentByUid(attachmentUid: Long): Flow<AttachmentEntity> {
        return sourceManager.fetchAttachmentByUid(attachmentUid)
    }

    override fun fetchAttachments(): Flow<List<AttachmentEntity>> {
        return sourceManager.fetchAttachments()
    }
}