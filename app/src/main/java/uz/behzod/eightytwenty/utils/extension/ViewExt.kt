package uz.behzod.eightytwenty.utils.extension

import android.content.Context
import android.graphics.Paint
import android.net.Uri
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
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
 * An extension function used to set the strikethrough effect of the drawn text
 * in the view, and the status determines whether to add or remove the effect
 * to the text.
 */
