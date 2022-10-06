package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val sourceManager: LocalSourceManager
): ImageRepository {
    override suspend fun insertNoteImage(image: NoteImageEntity): Long {
        return sourceManager.insertNoteImage(image)
    }

    override suspend fun updateNoteImage(image: NoteImageEntity) {
        return sourceManager.updateNoteImage(image)
    }

    override suspend fun deleteNoteImage(image: NoteImageEntity){
        return sourceManager.deleteNoteImage(image)
    }

    override fun fetchImagesByNoteUid(uuid: Long): Flow<NoteImageEntity> {
        return sourceManager.fetchImagesByNoteUid(uuid)
    }

    override fun fetchNoteImages(): Flow<List<NoteImageEntity>> {
        return sourceManager.fetchNoteImages()
    }
}