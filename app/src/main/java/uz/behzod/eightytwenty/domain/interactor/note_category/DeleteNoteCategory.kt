package uz.behzod.eightytwenty.domain.interactor.note_category

import uz.behzod.eightytwenty.domain.NoteCategoryDomainModel

interface DeleteNoteCategory {
    suspend operator fun invoke(data: NoteCategoryDomainModel)
}