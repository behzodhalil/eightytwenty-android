package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.*
import uz.behzod.eightytwenty.data.local.entities.asListOfDomain
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class SearchNotesImpl @Inject constructor(
    private val noteRepository: NoteRepository,
    private val dispatcherProvider: IDispatcherProvider
): SearchNotes {

    override fun invoke(query: String): Flow<List<NoteDomainModel>> {
        return flow {
            noteRepository.searchNotes(query).collect {
                if (it.isNotEmpty()) {
                    emit(it.asListOfDomain())
                }
            }
        }.flowOn(dispatcherProvider.io)
    }
}