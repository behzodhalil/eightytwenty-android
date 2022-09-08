package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

interface FetchTrashedNotes {
    operator fun invoke(): Flow<List<NoteDomainModel>>
}