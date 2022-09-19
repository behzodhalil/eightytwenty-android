package uz.behzod.eightytwenty.domain.interactor.task_catalog

interface DecrementTaskCount {
    suspend operator fun invoke(uid: Long)
}