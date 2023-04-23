package io.behzod.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Authentication(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {

    Log.d("Compose", "Inside the Auth composable")
    Log.d("Compose", "Ui state toggle ${viewModel.uiState.authenticationMode}")
    AuthenticationContent(
        modifier = Modifier.fillMaxWidth(),
        authenticationState = viewModel.uiState,
        handleEvent = viewModel::handleEvent,
        formValid = viewModel.formValid()
    )
}

@Composable
fun AuthenticationContent(
    modifier: Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit,
    formValid: Boolean,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (authenticationState.isLoading) {
            CircularProgressIndicator()
        } else {
            AuthenticationForm(
                modifier = modifier,
                authenticationMode = authenticationState.authenticationMode,
                email = authenticationState.email,
                name = authenticationState.name,
                password = authenticationState.password,
                enabledAuthentication = formValid,
                onEmailChanged = { email ->
                    handleEvent(
                        AuthenticationEvent.EmailChanged(email)
                    )
                },
                onNameChanged = { name ->
                    handleEvent(AuthenticationEvent.NameChanged(name))
                },
                onPasswordChanged = { password ->
                    handleEvent(AuthenticationEvent.PasswordChanged(password))
                },
                onAuthenticate = {
                    handleEvent(AuthenticationEvent.Authenticate)
                },
                completedPasswordRequirements = authenticationState.passwordRequirements,
                onNextClick = {
                    handleEvent(AuthenticationEvent.Authenticate)
                },
                onToggleMode = {
                    handleEvent(AuthenticationEvent.ToggleAuthenticationMode)
                }
            )
        }


    }
}

@Preview (showBackground = true)
@Composable
fun AuthenticationPreview() {
    AuthenticationContent(authenticationState = AuthenticationState(), handleEvent = {}, formValid = false)
}
