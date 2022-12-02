package uz.behzod.eightytwenty.utils.extension

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}

inline fun <reified T : Activity> Activity.startAndFinishActivity(
    block: Intent.() -> Unit = {},
) {
    startActivity(Intent(this, T::class.java).apply(block))
    finish()
}
