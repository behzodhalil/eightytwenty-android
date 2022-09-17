package uz.behzod.eightytwenty.utils.extension

import timber.log.Timber

inline fun debug(t: Throwable? = null, message : () -> String) {
    return log {
        Timber.d(t,message())
    }
}

inline fun info(message: () -> String) {
    return log {
        Timber.i(message())
    }
}

inline fun failure(t: Throwable? = null, message: () -> String) {
    return log {
        Timber.e(t,message())
    }
}

inline fun warning(message: () -> String,vararg args: Any? ) {
    return log {
        Timber.w(message(),args)
    }
}

@PublishedApi
internal inline fun log(block : () -> Unit) {
    if (Timber.treeCount > 0) block()
}
