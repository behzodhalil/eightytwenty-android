package uz.behzod.eightytwenty.domain.interactor.note

import uz.behzod.eightytwenty.domain.model.NoteDomainModel

interface InsertNote {
    suspend operator fun invoke(data: NoteDomainModel)
}