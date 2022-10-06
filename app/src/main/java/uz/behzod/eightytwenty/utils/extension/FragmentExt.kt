package uz.behzod.eightytwenty.utils.extension

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

val Fragment.navController: NavController
    get() = this.findNavController()

fun Fragment.navigateTo(route: NavDirections) {
    this.findNavController().navigate(directions = route)
}

fun Fragment.navigateTo(resId: Int) {
    this.findNavController().navigate(resId = resId)
}

val Fragment.packageManager
    get() = activity?.packageManager

fun Fragment.transaction(fragment: DialogFragment) {
    this
        .childFragmentManager
        .beginTransaction()
        .add(fragment, "")
        .commitAllowingStateLoss()
}

val Fragment.supportFragmentManager
    get() = requireActivity().supportFragmentManager