package uz.behzod.eightytwenty.utils.extension

import android.graphics.Paint
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

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