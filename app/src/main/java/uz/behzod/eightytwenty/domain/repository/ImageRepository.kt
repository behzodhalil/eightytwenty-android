package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

interface ImageRepository {
    suspend fun insertNoteImage(image: NoteImageEntity)
    suspend fun insertNoteImages(images: List<NoteImageEntity>)
    suspend fun updateNoteImage(image: NoteImageEntity)
    suspend fun deleteNoteImage(image: NoteImageEntity)
    fun fetchImageByNoteUid(uuid: Long): Flow<NoteImageEntity>
    fun fetchImagesByNoteByNoteUid(uuid: Long): Flow<List<NoteImageEntity>>
    fun fetchNoteImages(): Flow<List<NoteImageEntity>>
}