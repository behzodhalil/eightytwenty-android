package uz.behzod.eightytwenty.domain.interactor.note

import android.util.Log
import kotlinx.coroutines.flow.*
import uz.behzod.eightytwenty.data.local.entities.asDomain
import uz.behzod.eightytwenty.data.local.entities.asListOfDomain
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchNotesImpl @Inject constructor(
    private val iNoteRepository: NoteRepository,
    private val dispatcherProvider: IDispatcherProvider
) : FetchNotes {

    override fun invoke(): Flow<List<NoteDomainModel>> {
        return flow {
            iNoteRepository.fetchAllNotes().collect {
                if (it.isNotEmpty()) {
                    emit(it.asListOfDomain())
                }
            }
        }.flowOn(dispatcherProvider.io)
    }
}