package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchNoteRelationByUid @Inject constructor(
    private val noteRepository: NoteRepository,
    private val dispatcher: IDispatcherProvider
) : FetchNoteRelationByUid {

    override fun execute(noteUid: Long): Flow<NoteRelation> {
        return flow {
            noteRepository.fetchNoteRelationByUid(noteUid).collect { note: NoteRelation? ->
                if (note != null) {
                    emit(note)
                }
            }
        }.flowOn(dispatcher.io)
    }
}