package uz.behzoddev.ui_collapsible_calendar

sealed interface ExpandState {
    object More: ExpandState
    object Less: ExpandState
    object Intermediate: ExpandState
}