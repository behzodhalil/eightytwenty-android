package uz.behzod.eightytwenty.features.sign_in

import uz.behzod.eightytwenty.core.state.ViewState

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val isLoaded: Boolean = false
): ViewState {
    companion object {
        val Empty = SignInState()
    }
}