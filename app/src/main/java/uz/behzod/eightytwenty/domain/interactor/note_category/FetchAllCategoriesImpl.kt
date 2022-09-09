package uz.behzod.eightytwenty.domain.interactor.note_category

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.asListOfDomain
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchAllCategoriesImpl @Inject constructor(
    private val iNoteCategoryRepository: NoteCategoryRepository,
    private val dispatcherProvider: IDispatcherProvider
): FetchAllCategories {

    override fun invoke(): Flow<List<NoteCategoryDomainModel>> {
        return flow {
            iNoteCategoryRepository.fetchAllCategories().collect {
                if (it.isNotEmpty()) {
                    emit(it.asListOfDomain())
                }
            }

        }.flowOn(dispatcherProvider.io)
    }
}