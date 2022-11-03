package uz.behzod.eightytwenty.domain.interactor.note

import uz.behzod.eightytwenty.domain.model.NoteDomainModel

interface MoveToGroupNote {
    suspend fun execute(notes: List<NoteDomainModel>, groupUid: Long)
}