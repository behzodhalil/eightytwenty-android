package uz.behzod.eightytwenty.domain.interactor.note

import uz.behzod.eightytwenty.domain.NoteDomainModel

interface UpdateNote {
    suspend operator fun invoke(data: NoteDomainModel)
}