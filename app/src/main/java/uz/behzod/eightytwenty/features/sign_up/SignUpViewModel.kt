package uz.behzod.eightytwenty.features.sign_up

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.entities.UserEntity
import uz.behzod.eightytwenty.domain.interactor.login.CreateUserWithEmailAndPassword
import uz.behzod.eightytwenty.domain.interactor.login.InsertUser
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val createUserWithEmailAndPassword: CreateUserWithEmailAndPassword,
    private val firebaseAuth: FirebaseAuth,
    private val defaultInsertUser: InsertUser
) : ReduxViewModel<SignUpUiState>(
    initialState = SignUpUiState()
) {

    fun createUser() {
        val email = currentState.email
        val password = currentState.password
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    modifyState { state ->
                        state.copy(
                            isSuccess = true
                        )
                    }
                } else {
                    modifyState { state ->
                        state.copy(
                            isSuccess = false,
                            isFailed = true
                        )
                    }
                }
            }
    }

    fun insertUser() {
        viewModelScope.launch {
            val name = currentState.name
            val uid = currentState.uid

            runCatching {
                defaultInsertUser.execute(
                    UserEntity(name = name, uid = uid)
                )
            }.onSuccess {
                modifyState { state ->
                    state.copy(
                        isSaved = true
                    )
                }
            }.onFailure {
                modifyState { state ->
                    state.copy(
                        isSaveFailed = false
                    )
                }
            }

        }
    }
}
