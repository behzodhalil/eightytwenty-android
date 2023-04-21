package io.behzod.ui

data class AuthenticationState(
    val authenticationMode: AuthenticationMode = AuthenticationMode.SIGN_IN,
    val email: String? = null,
    val name: String? = null,
    val password: String? = null,
    val passwordRequirements: List<PasswordRequirements> = emptyList(),
    val nameRequirements: List<NameRequirements> = emptyList(),
    val isLoading: Boolean = false,
    val failure: String? = null,
)
