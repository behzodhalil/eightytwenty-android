package uz.behzod.eightytwenty.domain.interactor.login

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.core.LoginState
import uz.behzod.eightytwenty.domain.repository.LoginRepository
import javax.inject.Inject

class DefaultCreateUserWithEmailAndPassword @Inject constructor(
    private val loginRepository: LoginRepository
): CreateUserWithEmailAndPassword {

    override fun execute(email: String, password: String): Flow<LoginState> {
        return loginRepository.createUserWithEmailAndPassword(email, password)
    }
}