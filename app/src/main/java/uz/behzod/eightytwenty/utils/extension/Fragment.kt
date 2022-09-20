package uz.behzod.eightytwenty.utils.extension

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

val Fragment.navController: NavController
    get() = this.findNavController()

val Fragment.packageManager
    get() = activity?.packageManager

fun Fragment.transaction(fragment: DialogFragment) {
    this
        .childFragmentManager
        .beginTransaction()
        .add(fragment,"")
        .commitAllowingStateLoss()
}