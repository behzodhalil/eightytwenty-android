package uz.behzod.eightytwenty.domain.interactor.login

import uz.behzod.eightytwenty.domain.repository.LoginRepository
import javax.inject.Inject

class DefaultSignOut @Inject constructor(
    private val loginRepository: LoginRepository
): SignOut {

    override suspend fun execute() {
        loginRepository.signOut()
    }
}