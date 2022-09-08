package uz.behzod.eightytwenty.domain.interactor.note_category

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.behzod.eightytwenty.data.local.entities.asListOfDomain
import uz.behzod.eightytwenty.domain.model.CategoryAndNotesDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchAllCategoriesAndNotesImpl @Inject constructor(
    private val iNoteCategoryRepository: NoteCategoryRepository,
    private val dispatcherProvider: IDispatcherProvider
): FetchAllCategoriesAndNotes {

    override fun invoke(): Flow<List<CategoryAndNotesDomainModel>> {
        return flow {
            iNoteCategoryRepository.fetchAllCategoriesAndNotes()
                .map { it.asListOfDomain() }
                .flowOn(dispatcherProvider.io)
        }
    }
}