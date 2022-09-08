package uz.behzod.eightytwenty.domain.interactor.note_category

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DeleteNoteCategoryImpl @Inject constructor(
    private val iNoteCategoryRepository: NoteCategoryRepository,
    private val dispatcherProvider: IDispatcherProvider
): DeleteNoteCategory {

    override suspend fun invoke(data: NoteCategoryDomainModel) {
        return withContext(dispatcherProvider.io) {
            iNoteCategoryRepository.deleteNoteCategory(data.asEntity())
        }
    }
}