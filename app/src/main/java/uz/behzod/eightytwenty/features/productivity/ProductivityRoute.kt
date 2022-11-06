package uz.behzod.eightytwenty.features.productivity

sealed interface ProductivityRoute {
    object SettingRoute: ProductivityRoute
    object SelectProductivityRoute: ProductivityRoute
}