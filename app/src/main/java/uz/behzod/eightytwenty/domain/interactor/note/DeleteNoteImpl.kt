package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DeleteNoteImpl @Inject constructor(
    private val iNoteRepository: NoteRepository,
    private val iNoteCategoryRepository: NoteCategoryRepository,
    private val dispatcherProvider: IDispatcherProvider
): DeleteNote {

    override suspend fun invoke(data: NoteDomainModel) {
        return withContext(dispatcherProvider.io) {
            iNoteRepository.deleteNote(data.asEntity())
            if (!data.isTrashed) {
                iNoteCategoryRepository.decrementNoteCount(data.categoryId)
            }
        }
    }
}