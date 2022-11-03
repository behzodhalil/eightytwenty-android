package uz.behzod.eightytwenty.domain.interactor.login

import uz.behzod.eightytwenty.data.local.entities.UserEntity
import uz.behzod.eightytwenty.domain.repository.LoginRepository
import javax.inject.Inject

class DefaultDeleteUser @Inject constructor(
    private val loginRepository: LoginRepository
): DeleteUser {

    override suspend fun execute(user: UserEntity) {
        loginRepository.deleteUser(user)
    }
}