package uz.behzod.eightytwenty.domain.interactor.login

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.core.LoginState

interface CreateUserWithEmailAndPassword {
    fun execute(email: String, password: String):Flow<LoginState>
}