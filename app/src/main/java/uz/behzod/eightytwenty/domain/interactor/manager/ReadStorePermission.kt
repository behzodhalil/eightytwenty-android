package uz.behzod.eightytwenty.domain.interactor.manager

interface ReadStorePermission {
    operator fun invoke(): Boolean
}