package uz.behzod.eightytwenty.core

sealed interface LoginState {
    object Success: LoginState
    object Failed: LoginState
}