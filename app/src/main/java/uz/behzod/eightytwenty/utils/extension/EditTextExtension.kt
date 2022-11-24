package uz.behzod.eightytwenty.utils.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.checkNotNull(action: () -> Unit) {
    val parentLayout = this.parent.parent as TextInputLayout
    parentLayout.endIconDrawable = null

    this.onChange { this.error = null }

    if (this.text.toString().trim().isEmpty()) {
        action()
    }
}

fun EditText.checkNotNull(action: () -> Unit): Boolean {
    this.onChange { this.error = null }

    return if (this.text.toString().trim().isEmpty()) {
        action()
        false
    } else {
        true
    }
}

fun AutoCompleteTextView.checkNotNull(action: () -> Unit): Boolean {
    this.onChange { this.error = null }

    return  if (this.text.toString().trim().isEmpty()) {
        action()
        false
    } else  {
        true
    }
}

fun EditText.onChange(cb: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}
