package uz.behzod.eightytwenty.domain.interactor.note

import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

interface DeleteNote {
    suspend operator fun invoke(data: NoteDomainModel,images: List<NoteImageEntity> = emptyList())
}