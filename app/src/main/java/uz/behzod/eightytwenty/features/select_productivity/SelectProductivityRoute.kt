package uz.behzod.eightytwenty.features.select_productivity

import uz.behzod.eightytwenty.features.productivity.ProductivityRoute

sealed interface SelectProductivityRoute {
    object BackRoute: SelectProductivityRoute
}