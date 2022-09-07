package uz.behzod.eightytwenty.domain.interactor.note_category

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.repository.CategoryAndNotesDomainModel

interface FetchAllCategoriesAndNotes {
    operator fun invoke(): Flow<List<CategoryAndNotesDomainModel>>
}