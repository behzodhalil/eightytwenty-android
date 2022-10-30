package uz.behzoddev.ui_toast

sealed interface XToastStyle {
    object Success: XToastStyle
    object Info: XToastStyle
    object Warning: XToastStyle
    object Error: XToastStyle
}