package uz.behzod.eightytwenty.utils.extension

import androidx.annotation.IdRes
import androidx.navigation.NavController

fun NavController.isInBackStack(@IdRes routeId: Int): Boolean = runCatching {
    getBackStackEntry(routeId)
}.isSuccess