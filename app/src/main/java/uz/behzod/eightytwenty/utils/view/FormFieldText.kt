package uz.behzod.eightytwenty.utils.view

import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import reactivecircus.flowbinding.android.widget.textChanges

/**
 * This class implementation for text type fields.
 */
class FormFieldText(
    scope: CoroutineScope,
    private val textInputLayout: TextInputLayout,
    private val textInputText: TextInputEditText,
    private val validation: suspend (String?) -> String? = { null },
) : FormField<String>() {

    init {
        textInputText.textChanges().skipInitialValue().onEach { text ->
            clearError()
            stateInternal.update { text.toString() }
        }.launchIn(scope)
    }

    var isEnabled: Boolean
        get() = textInputLayout.isEnabled
        set(value) {
            textInputLayout.isEnabled = value
        }

    var isVisible: Boolean
        get() = textInputLayout.isVisible
        set(value) {
            textInputLayout.isVisible = value
        }

    var value: String?
        get() = stateInternal.value
        set(value) {
            textInputText.setText(value)
        }

    override suspend fun validate(isFocusFailed: Boolean): Boolean {
        if (!isVisible) {
            return true
        }

        val errorValue = try {
            validation(stateInternal.value)
        } catch (e: Throwable) {
            e.message
        }

        val result = errorValue == null
        if (result) {
            clearError()
        } else {
            textInputLayout.error = errorValue
            if (isFocusFailed) {
                textInputText.requestFocus()
            }
        }
        isValidInternal.update { result }
        return result

    }

    override fun clearError() {
        if (textInputLayout.error != null) {
            textInputLayout.error = null
            textInputLayout.isErrorEnabled = false
        }
    }

    override fun clearFocus() {
        textInputText.clearFocus()
    }

    override fun disable() {
        isEnabled = false
    }

    override fun enable() {
        isEnabled = true
    }
}