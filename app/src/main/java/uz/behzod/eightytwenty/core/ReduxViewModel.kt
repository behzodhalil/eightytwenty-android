package uz.behzod.eightytwenty.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import uz.behzod.eightytwenty.core.state.ViewEffect
import uz.behzod.eightytwenty.core.state.ViewState

abstract class ReduxViewModel<VS: ViewState>(initialState: VS): ViewModel() {

    private var _state: MutableStateFlow<VS> = MutableStateFlow(initialState)
    val state: StateFlow<VS> = _state.asStateFlow()

    val currentState: VS get() = state.value

    protected fun modifyState (
        action: (initialState: VS) -> VS
    ): VS {
        return _state.updateAndGet(action)
    }

    
}