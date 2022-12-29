package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.NoteFTS
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultSearchNoteFTS @Inject constructor(
    private val repository: NoteRepository,
    private val dispatcherProvider: IDispatcherProvider,
) : SearchNoteFTS {

    override fun execute(query: String): Flow<List<NoteFTS>> {
        return flow {
            repository.searchNoteFTS(query).collect {
                emit(it)
            }
        }.flowOn(dispatcherProvider.io)
    }
}
