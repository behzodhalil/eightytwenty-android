package uz.behzod.eightytwenty.utils.extension

import androidx.annotation.ArrayRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.afollestad.materialdialogs.utils.MDUtil.getStringArray
import uz.behzod.eightytwenty.R
import java.time.ZonedDateTime

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

fun Fragment.stringArray(@ArrayRes array: Int): Array<String> {
    return this.requireContext().getStringArray(array)
}

fun Fragment.datePicker(action: (timestamp: ZonedDateTime) -> Unit, finish:() -> Unit) {
    MaterialDialog(requireContext()).show {
        lifecycleOwner(viewLifecycleOwner)
        datePicker(
            requireFutureDate = true,
            currentDate = ZonedDateTime.now().toCalendar()
        ) { _, timestamp ->
            action(timestamp.toZonedDateTime() ?: ZonedDateTime.now())
        }
        positiveButton(R.string.text_btn_done) {
            dismiss()
            finish()
        }
    }
}
