package uz.behzod.eightytwenty.domain.interactor.login

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.UserEntity

interface FetchUser {
    fun execute(): Flow<UserEntity>
}