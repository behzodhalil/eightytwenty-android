package uz.behzod.eightytwenty.domain.interactor.note_category

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.NoteCategoryDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class UpdateNoteCategoryImpl @Inject constructor(
    private val iNoteCategoryRepository: NoteCategoryRepository,
    private val dispatcherProvider: IDispatcherProvider
): UpdateNoteCategory {

    override suspend fun invoke(data: NoteCategoryDomainModel) {
        return withContext(dispatcherProvider.io) {
            iNoteCategoryRepository.updateNoteCategory(data.asEntity())
        }
    }
}