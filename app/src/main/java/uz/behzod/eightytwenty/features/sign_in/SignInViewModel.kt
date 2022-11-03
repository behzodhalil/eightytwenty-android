package uz.behzod.eightytwenty.features.sign_in

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.LoginState
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.login.SignInEmailWithPassword
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ReduxViewModel<SignInState>(
    initialState = SignInState.Empty
) {
    fun updateEmail(value: String) {
        modifyState { state -> state.copy(email = value) }
    }

    fun updatePassword(value: String) {
        modifyState { state -> state.copy(password = value) }
    }

    fun signIn() {
        viewModelScope.launch {
            val email = currentState.email
            val password = currentState.password

            firebaseAuth.signInWithEmailAndPassword(
                email, password
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    modifyState { state ->
                        state.copy(isSuccess = true)
                    }
                } else {
                    modifyState { state ->
                        state.copy(isFailed = true)
                    }
                }
            }.addOnFailureListener {
                modifyState { state ->
                    state.copy(isFailed = true, isSuccess = false)
                }
            }
        }
    }
}