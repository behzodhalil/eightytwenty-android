package uz.behzod.eightytwenty.features.sign_up

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.utils.extension.Zero

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val uid: Long = Long.Zero,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val isLoaded: Boolean = false,
    val isCreated: Boolean = false,
    val isCreateFailed: Boolean = false
): ViewState