package uz.behzod.eightytwenty.domain.interactor.note_category

import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel

interface InsertNoteCategory {
    suspend operator fun invoke(data: NoteCategoryDomainModel)
}