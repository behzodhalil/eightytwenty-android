package io.behzod.ui

sealed interface AuthenticationEvent {
    object ToggleAuthenticationMode : AuthenticationEvent
    data class EmailChanged(val email: String) : AuthenticationEvent
    data class PasswordChanged(val password: String) : AuthenticationEvent
    data class NameChanged(val name: String) : AuthenticationEvent
    object Authenticate: AuthenticationEvent
    object FailureDismissed: AuthenticationEvent
}
