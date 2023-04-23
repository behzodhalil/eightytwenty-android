package io.behzod.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.behzod.domain.ValidationForm
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val validatorForm: ValidationForm,
) : ViewModel() {

    var uiState by mutableStateOf(AuthenticationState())
        private set

    fun handleEvent(event: AuthenticationEvent) {
        when (event) {
            is AuthenticationEvent.ToggleAuthenticationMode -> {
                toggleAuthenticationMode()
            }
            is AuthenticationEvent.EmailChanged -> {
                updateEmail(event.email)
            }
            is AuthenticationEvent.NameChanged -> {
                updateName(event.name)
            }
            is AuthenticationEvent.PasswordChanged -> {
                updatePassword(event.password)
            }
            AuthenticationEvent.Authenticate -> {
                authenticate()
            }
            AuthenticationEvent.FailureDismissed -> {
                dismissFailure()
            }

        }
    }

    private fun authenticate() {
        uiState = uiState.copy(
            isLoading = true
        )
        viewModelScope.launch {

        }
    }

    private fun dismissFailure() {
        uiState = uiState.copy(
            failure = null
        )
    }

    private fun toggleAuthenticationMode() {
        val authenticationMode = uiState.authenticationMode

        val newAuthenticationMode: AuthenticationMode = if (
            authenticationMode == AuthenticationMode.SIGN_IN
        ) {
            AuthenticationMode.SIGN_UP
        } else {
            AuthenticationMode.SIGN_IN
        }
        uiState = uiState.copy(
            authenticationMode = newAuthenticationMode
        )
    }

    private fun updateEmail(email: String) {
        uiState = uiState.copy(
            email = email
        )
    }

    private fun updatePassword(password: String) {
        val requirements = mutableListOf<PasswordRequirements>()

        if (password.length > MIN_CHARACTERS_LENGTH) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }

        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }

        uiState = uiState.copy(
            password = password,
            passwordRequirements = requirements
        )
    }

    private fun updateName(name: String) {
        val requirements = mutableListOf<NameRequirements>()

        if (name.any { it.isUpperCase() }) {
            requirements.add(NameRequirements.CAPITAL_LETTER)
        }

        if (name.all { it.isLetter() }) {
            requirements.add(NameRequirements.NOT_DIGIT)
        }

        uiState = uiState.copy(
            name = name,
            nameRequirements = requirements
        )
    }

    fun formValid(): Boolean {
        return validatorForm(name = uiState.name, email = uiState.email, password = uiState.password)
    }

    companion object {
        private const val MIN_CHARACTERS_LENGTH = 7
    }
}

