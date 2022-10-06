package uz.behzod.eightytwenty.utils.extension

import timber.log.Timber

inline fun printDebug(t: Throwable? = null, message : () -> String) {
    return log {
        Timber.d(t,message())
    }
}

inline fun printInfo(message: () -> String) {
    return log {
        Timber.i(message())
    }
}

inline fun printFailure(t: Throwable? = null, message: () -> String) {
    return log {
        Timber.e(t,message())
    }
}

inline fun printWarning(message: () -> String, vararg args: Any? ) {
    return log {
        Timber.w(message(),args)
    }
}

@PublishedApi
internal inline fun log(block : () -> Unit) {
    if (Timber.treeCount > 0) block()
}
