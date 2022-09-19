package uz.behzod.eightytwenty.domain.interactor.task_catalog

interface IncrementTaskCount {
    suspend operator fun invoke(uid: Long)
}