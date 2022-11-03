package uz.behzod.eightytwenty.domain.interactor.login

import uz.behzod.eightytwenty.data.local.entities.UserEntity
import uz.behzod.eightytwenty.domain.repository.LoginRepository
import javax.inject.Inject

class DefaultInsertUser @Inject constructor(
    private val loginRepository: LoginRepository
): InsertUser {

    override suspend fun execute(user: UserEntity) {
        return loginRepository.insertUser(user)
    }
}