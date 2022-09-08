package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.asListOfDomain
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.DispatcherProvider
import javax.inject.Inject

class FetchNotesByCategoryIdImpl @Inject constructor(
    private val iNoteRepository: NoteRepository,
    private val dispatcherProvider: DispatcherProvider
) : FetchNotesByCategoryId {

    override fun invoke(categoryId: Long): Flow<List<NoteDomainModel>> {
        return flow {
            iNoteRepository.fetchNotesByCategoryId(categoryId).collect { result ->
                if (result.isNotEmpty()) {
                    emit(result.asListOfDomain())
                }
            }
        }.flowOn(dispatcherProvider.io)
    }
}