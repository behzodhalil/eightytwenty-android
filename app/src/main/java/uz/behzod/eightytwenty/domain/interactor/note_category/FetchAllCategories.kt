package uz.behzod.eightytwenty.domain.interactor.note_category

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel

interface FetchAllCategories {
    operator fun invoke(): Flow<List<NoteCategoryDomainModel>>
}