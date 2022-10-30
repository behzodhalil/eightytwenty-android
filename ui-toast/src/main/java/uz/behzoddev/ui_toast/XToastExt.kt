package uz.behzoddev.ui_toast

import androidx.fragment.app.Fragment

fun Fragment.infoMessage(message: String) {
    XToast.Builder(this.requireContext())
        .style(XToastStyle.Info)
        .duration(XToast.SHORT_DURATION)
        .message(message)
        .title("Информация")
        .build()
}

fun Fragment.errorMessage(message: String) {
    XToast.Builder(this.requireContext())
        .style(XToastStyle.Error)
        .duration(XToast.SHORT_DURATION)
        .message(message)
        .title("Ошибка")
        .build()
}

fun Fragment.successMessage(message: String) {
    XToast.Builder(this.requireContext())
        .style(XToastStyle.Success)
        .duration(XToast.SHORT_DURATION)
        .message(message)
        .title("Успех")
        .build()
}

fun Fragment.warningMessage(message: String) {
    XToast.Builder(this.requireContext())
        .style(XToastStyle.Warning)
        .duration(XToast.SHORT_DURATION)
        .message(message)
        .title("Предупреждение")
        .build()
}
