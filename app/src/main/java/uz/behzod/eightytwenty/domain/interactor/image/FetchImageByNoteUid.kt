package uz.behzod.eightytwenty.domain.interactor.image

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

interface FetchImageByNoteUid {
    fun execute(uid: Long): Flow<NoteImageEntity>
}