package uz.behzod.eightytwenty.features.setting

import dagger.hilt.android.lifecycle.HiltViewModel
import uz.behzod.eightytwenty.core.ReduxViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor() : ReduxViewModel<ThemeState>(initialState = ThemeState()) {

    fun hasLight(value: Boolean) {
        modifyState { state -> state.copy(isLight = value) }
    }

    fun hasDark(value: Boolean) {
        modifyState { state -> state.copy(isDark = value) }
    }

    fun hasDone(value: Boolean) {
        modifyState { state -> state.copy(isDone = value) }
    }
}
