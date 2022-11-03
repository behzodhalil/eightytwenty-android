package uz.behzod.eightytwenty.domain.interactor.login

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.UserEntity
import uz.behzod.eightytwenty.domain.repository.LoginRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchUser @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatchers: IDispatcherProvider
) : FetchUser {

    override fun execute(): Flow<UserEntity> {
        return flow {
            loginRepository.fetchUser().collect {
                emit(it)
            }
        }.flowOn(dispatchers.io)
    }
}