package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class UpdateNoteImpl @Inject constructor(
    private val iNoteRepository: NoteRepository,
    private val dispatcherProvider: IDispatcherProvider
) : UpdateNote {

    override suspend fun invoke(data: NoteDomainModel) {
        return withContext(dispatcherProvider.io) {
            iNoteRepository.updateNote(data.asEntity())
        }
    }
}