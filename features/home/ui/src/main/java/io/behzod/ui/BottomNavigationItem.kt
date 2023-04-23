package io.behzod.ui



sealed class BottomNavigationItem(val route: String, val icon: Int, val title: Int) {
    object Note : BottomNavigationItem(route = "note", io.behzod.features.R.drawable.ic_check, io.behzod.features.R.string.note)
    object Task : BottomNavigationItem(route = "task", io.behzod.features.R.drawable.ic_check, io.behzod.features.R.string.task)
    object Reminder : BottomNavigationItem(route = "reminder", io.behzod.features.R.drawable.ic_check, io.behzod.features.R.string.reminder)
    object Habit : BottomNavigationItem(route = "habit", io.behzod.features.R.drawable.ic_check, io.behzod.features.R.string.habit)
    object Productivity : BottomNavigationItem(route = "productivity", io.behzod.features.R.drawable.ic_check, io.behzod.features.R.string.productivity)

}
