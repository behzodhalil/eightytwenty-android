package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

interface ImageRepository {
    suspend fun insertNoteImage(image: NoteImageEntity): Long
    suspend fun updateNoteImage(image: NoteImageEntity)
    suspend fun deleteNoteImage(image: NoteImageEntity)
    fun fetchImagesByNoteUid(uuid: Long): Flow<NoteImageEntity>
    fun fetchNoteImages(): Flow<List<NoteImageEntity>>
}