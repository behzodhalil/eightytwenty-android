package uz.behzod.eightytwenty.features.setting

import uz.behzod.eightytwenty.core.state.ViewState

data class ThemeState(
    val isLight: Boolean = false,
    val isDark: Boolean = false,
    val isDone: Boolean = false
): ViewState
