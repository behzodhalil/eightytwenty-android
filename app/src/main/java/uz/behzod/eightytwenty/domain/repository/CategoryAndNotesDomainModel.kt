package uz.behzod.eightytwenty.domain.repository

import uz.behzod.eightytwenty.domain.NoteCategoryDomainModel
import uz.behzod.eightytwenty.domain.NoteDomainModel

data class CategoryAndNotesDomainModel(
    val category : NoteCategoryDomainModel,
    val listNotes: List<NoteDomainModel>
)
