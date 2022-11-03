package uz.behzod.eightytwenty.domain.interactor.login

import uz.behzod.eightytwenty.data.local.entities.UserEntity

interface InsertUser {
    suspend fun execute(user: UserEntity)
}