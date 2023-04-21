package io.behzod.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Authentication() {
    val viewModel: AuthenticationViewModel = viewModel()

    AuthenticationContent(
        modifier = Modifier.fillMaxWidth(),
        authenticationState = viewModel.uiState,
        handleEvent = viewModel::handleEvent
    )
}

@Composable
fun AuthenticationContent(
    modifier: Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit,
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
                onEmailChanged = { email ->
                    handleEvent(
                        AuthenticationEvent.EmailChanged(email)
                    )
                },
                onNameChanged = {},
                onPasswordChanged = {}
            )
        }
    }
}
