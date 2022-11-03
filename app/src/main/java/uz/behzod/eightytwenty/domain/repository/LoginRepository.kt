package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.core.LoginState
import uz.behzod.eightytwenty.data.local.entities.UserEntity

interface LoginRepository {

    fun createUserWithEmailAndPassword(email: String, password: String): Flow<LoginState>
    fun signInWithEmailAndPassword(email: String, password: String): Flow<LoginState>
    suspend fun signOut()
    suspend fun insertUser(user: UserEntity)
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
    fun fetchUser(): Flow<UserEntity>
}