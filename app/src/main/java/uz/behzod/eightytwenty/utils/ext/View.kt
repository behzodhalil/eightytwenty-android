package uz.behzod.eightytwenty.utils.ext

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun TextView.asString(): String {
    return this.text.toString()
}

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
fun TextView.setStrikethrough(status: Boolean) {
    if (status) {
        this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        this.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

fun TextView.setTextColorFromResource(
    @ColorRes colorId: Int
) {
    this.setTextColor(ContextCompat.getColor(this.context,colorId))
}