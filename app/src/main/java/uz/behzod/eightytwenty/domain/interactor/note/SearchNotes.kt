package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

interface SearchNotes {
    operator fun invoke(query: String): Flow<List<NoteDomainModel>>
}