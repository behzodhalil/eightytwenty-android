package uz.behzod.eightytwenty.utils.extension

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat



val Context.layoutInflaterService: Any
    get() = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)

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

/**
 * An extension function used to set the strikethrough effect of the drawn text
 * in the view, and the status determines whether to add or remove the effect
 * to the text.
 */
