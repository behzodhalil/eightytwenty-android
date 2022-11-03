package uz.behzod.eightytwenty.domain.interactor.login

import uz.behzod.eightytwenty.data.local.entities.UserEntity

interface DeleteUser {
    suspend fun execute(user: UserEntity)
}