package uz.behzod.eightytwenty.domain.interactor.note

import uz.behzod.eightytwenty.domain.NoteDomainModel

interface InsertNote {
    suspend operator fun invoke(data: NoteDomainModel)
}