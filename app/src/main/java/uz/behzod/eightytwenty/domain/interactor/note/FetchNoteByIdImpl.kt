package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.asDomain
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchNoteByIdImpl @Inject constructor(
    private val iNoteRepository: NoteRepository,
    private val dispatcherProvider: IDispatcherProvider
): FetchNoteById {

    override fun invoke(noteId: Long): Flow<NoteDomainModel> {
        return flow {
            iNoteRepository.fetchNoteById(noteId).collect {
                emit(it.asDomain())
            }
        }.flowOn(dispatcherProvider.io)
    }
}