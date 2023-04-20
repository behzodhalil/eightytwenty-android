package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.behzod.eightytwenty.data.local.entities.NoteWithMatchInfo
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.helper.FtsHelper
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultSearchNoteFTS @Inject constructor(
    private val repository: NoteRepository,
    private val dispatcherProvider: IDispatcherProvider,
) : SearchNoteFTS {

    override fun execute(query: String): Flow<List<NoteWithMatchInfo>> {
        return repository.searchNoteFTS(FtsHelper.ftsQuery(query))
            .flowOn(dispatcherProvider.io)
            .map { notes ->
                notes
                    .sortedByDescending {
                        FtsHelper.calculateScore(it.matchInfo)
                    }
            }

    }
}
