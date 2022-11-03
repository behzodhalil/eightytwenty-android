package uz.behzod.eightytwenty.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.core.LoginState
import uz.behzod.eightytwenty.data.local.entities.UserEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.LoginRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultLoginRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dispatchers: IDispatcherProvider,
    private val sourceManager: LocalSourceManager
): LoginRepository {

    override fun createUserWithEmailAndPassword(email: String, password: String): Flow<LoginState> {
        return flow {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                emit(LoginState.Success)
            } catch (e: Exception) {
                emit(LoginState.Failed)
            }
        }.flowOn(dispatchers.io)
    }

    override fun signInWithEmailAndPassword(email: String, password: String): Flow<LoginState> {
        return flow {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        CoroutineScope(dispatchers.io).launch {
                            emit(LoginState.Success) }
                        }


            } catch (e: Exception) {
                emit(LoginState.Failed)
            }
        }.flowOn(dispatchers.io)
    }

    override suspend fun signOut() {
        withContext(dispatchers.io) {
            firebaseAuth.signOut()
        }
    }

    override suspend fun insertUser(user: UserEntity) {
        withContext(dispatchers.io) {
            sourceManager.insertUser(user)
        }
    }

    override suspend fun updateUser(user: UserEntity) {
        withContext(dispatchers.io) {
            sourceManager.updateUser(user)
        }
    }

    override suspend fun deleteUser(user: UserEntity) {
        withContext(dispatchers.io) {
            sourceManager.deleteUser(user)
        }
    }

    override fun fetchUser(): Flow<UserEntity> {
        return sourceManager.fetchUser()
    }
}