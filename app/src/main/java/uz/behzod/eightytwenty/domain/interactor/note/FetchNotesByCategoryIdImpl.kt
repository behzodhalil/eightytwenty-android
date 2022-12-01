package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchNotesByCategoryIdImpl @Inject constructor(
    private val iNoteRepository: NoteRepository,
    private val dispatcherProvider: IDispatcherProvider
) : FetchNotesByCategoryId {

    override fun invoke(categoryId: Long): Flow<List<NoteRelation>> {
        return flow {
            iNoteRepository.fetchNotesByCategoryId(categoryId).collect { result ->
                if (result.isNotEmpty()) {
                    emit(result)
                }
            }
        }.flowOn(dispatcherProvider.io)
    }
}
