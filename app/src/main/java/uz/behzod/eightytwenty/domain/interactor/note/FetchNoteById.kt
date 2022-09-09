package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

interface FetchNoteById {
    operator fun invoke(noteId: Long): Flow<NoteDomainModel>
}