package uz.behzod.eightytwenty.utils.extension

import android.content.Context
import android.graphics.Paint
import android.net.Uri
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment


val Context.layoutInflaterService: Any
    get() = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)

fun Context.getUriMimeType(uri: Uri): String? {
    return contentResolver.getType(uri)
}

fun Context.getUriExtension(uri: Uri): String? {
    val mimeType = contentResolver.getType(uri)
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(
        mimeType
    )
}

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun Context.showMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

/**
 * The extension function for set the background to a given resource.
 * The resource should refer to a Drawable object or
 * 0 to remove the background.
 */
fun View.drawable(@DrawableRes id: Int) = setBackgroundResource(id)


fun View.focusAndShowKeyboard() {

    fun View.showTheKeyboardNow() {
        if (isFocused) {
            post {
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }

    requestFocus()
    if (hasWindowFocus()) {
        showTheKeyboardNow()
    } else {
        viewTreeObserver.addOnWindowFocusChangeListener(
            object : ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    if (hasFocus) {
                        this@focusAndShowKeyboard.showTheKeyboardNow()
                        viewTreeObserver.removeOnWindowFocusChangeListener(this)
                    }
                }
            })
    }
}


fun View.showKeyboard() {
    val manager: InputMethodManager =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.showSoftInput(this, 0)
}
