package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchAllNoteRelation @Inject constructor(
    private val noteRepository: NoteRepository,
    private val dispatchers: IDispatcherProvider
): FetchAllNoteRelation {

    override fun execute(): Flow<List<NoteRelation>> {
        return flow {
            noteRepository.fetchAllNoteRelation().collect {
                if (it.isNotEmpty()) {
                    emit(it)
                }
            }
        }.flowOn(dispatchers.io)
    }
}