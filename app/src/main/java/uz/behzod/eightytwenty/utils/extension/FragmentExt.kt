package uz.behzod.eightytwenty.utils.extension

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
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

private const val leftPixels = 0
private const val topPixels = 0
private const val dimenAmount = 0.0F

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

fun DialogFragment.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(leftPixels, topPixels, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}



fun DialogFragment.setTransparent() {
    dialog?.window?.setDimAmount(dimenAmount)
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}
