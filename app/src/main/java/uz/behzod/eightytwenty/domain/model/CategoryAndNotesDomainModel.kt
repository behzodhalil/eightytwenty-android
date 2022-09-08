package uz.behzod.eightytwenty.domain.model


data class CategoryAndNotesDomainModel(
    val category : NoteCategoryDomainModel,
    val listNotes: List<NoteDomainModel>
)
