package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.NoteDomainModel


interface FetchNotesByCategoryId {
    operator fun invoke(categoryId: Long): Flow<List<NoteDomainModel>>
}