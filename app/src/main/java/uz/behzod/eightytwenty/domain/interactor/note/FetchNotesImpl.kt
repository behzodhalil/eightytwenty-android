package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.behzod.eightytwenty.data.local.entities.asListOfDomain
import uz.behzod.eightytwenty.domain.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchNotesImpl @Inject constructor(
    private val iNoteRepository: NoteRepository,
    private val dispatcherProvider: IDispatcherProvider
) : FetchNotes {

    override fun invoke(): Flow<List<NoteDomainModel>> {
        return flow {
            iNoteRepository.fetchAllNotes()
                .map { it.asListOfDomain() }
                .flowOn(dispatcherProvider.io)
        }
    }
}