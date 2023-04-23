package io.behzod.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String?,
    name: String?,
    password: String?,
    enabledAuthentication: Boolean,
    completedPasswordRequirements: List<PasswordRequirements>,
    onEmailChanged: (email: String) -> Unit,
    onNameChanged: (name: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onAuthenticate: () -> Unit,
    onNextClick: () -> Unit,
    onToggleMode: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = modifier.height(20.dp))
        AuthenticationTitle(
            modifier = modifier.fillMaxWidth(),
            authenticationMode = authenticationMode
        )

        val focusRequest = FocusRequester()

        Spacer(modifier = modifier.height(16.dp))
        Column(
            horizontalAlignment =
            Alignment.CenterHorizontally
        ) {
            EmailInput(
                modifier = modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequest),
                email = email,
                onEmailChanged = onEmailChanged
            ) {
                focusRequest.requestFocus()
            }

            if (authenticationMode == AuthenticationMode.SIGN_UP) {
                Spacer(modifier = modifier.height(16.dp))
                NameInput(
                    modifier = modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequest),
                    name = name,
                    onNameChanged = { name ->
                        onNameChanged(name)
                    }) {
                    focusRequest.requestFocus()
                }
            }

            Spacer(modifier = modifier.height(16.dp))
            PasswordInput(
                modifier = modifier
                    .focusRequester(focusRequest),
                password = password,
                onPasswordChanged = { password ->
                    onPasswordChanged(password)
                }) {
                onAuthenticate()
            }

            Spacer(modifier = modifier.height(16.dp))

            AnimatedVisibility(visible = authenticationMode == AuthenticationMode.SIGN_UP) {
                PasswordRequirement(
                    modifier = modifier.fillMaxWidth(),
                    requirements = completedPasswordRequirements)
            }

            Spacer(modifier = modifier.height(56.dp))

            AuthenticationButton(
                modifier = modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(12.dp),
                enableAuthentication = enabledAuthentication
            ) {
                onNextClick()
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        if (authenticationMode == AuthenticationMode.SIGN_IN) {
            ToggleAuthenticationMode() {
                onToggleMode()
            }
        }
        Spacer(modifier = modifier.height(56.dp))

    }
}

@Preview
@Composable
fun AuthenticationSignInFormPreview() {
    Surface {
        AuthenticationForm(
            authenticationMode = AuthenticationMode.SIGN_IN,
            email = "behzoddev@gmail.com",
            name = "",
            password = "",
            enabledAuthentication = false,
            onEmailChanged = {},
            onNameChanged = {},
            onPasswordChanged = {},
            onAuthenticate = {},
            completedPasswordRequirements = emptyList(),
            onNextClick = {},
            onToggleMode = {}
        )
    }

}

@Preview
@Composable
fun AuthenticationSignUpFormPreview() {
    Surface {
        AuthenticationForm(
            authenticationMode = AuthenticationMode.SIGN_UP,
            email = "behzoddev@gmail.com",
            name = "",
            password = "",
            enabledAuthentication = false,
            onEmailChanged = {},
            onNameChanged = {},
            onPasswordChanged = {},
            onAuthenticate = {},
            completedPasswordRequirements = listOf(PasswordRequirements.CAPITAL_LETTER,
                PasswordRequirements.NUMBER,
                PasswordRequirements.EIGHT_CHARACTERS),
            onNextClick = {},
            onToggleMode = {}
        )
    }

}


