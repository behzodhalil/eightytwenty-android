package uz.behzod.eightytwenty.domain.interactor.login

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.core.LoginState
import uz.behzod.eightytwenty.domain.repository.LoginRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultSignInEmailWithPassword @Inject constructor(
    private val loginRepository: LoginRepository
): SignInEmailWithPassword {

    override fun execute(email: String, password: String): Flow<LoginState> {
        return loginRepository.signInWithEmailAndPassword(email,password)
    }
}