package uz.behzod.eightytwenty.utils.view

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Base class for a different type of form fields.
 */
abstract class FormField<T> {
    protected val stateInternal = MutableStateFlow<T?>(null)
    protected val isValidInternal = MutableStateFlow(true)

    val state = stateInternal.asStateFlow()
    val isValid = isValidInternal.asStateFlow()

    abstract suspend fun validate(isFocusFailed: Boolean = true): Boolean

    open fun clearError() {}
    open fun clearFocus() {}
    open fun disable() {}
    open fun enable() {}
}