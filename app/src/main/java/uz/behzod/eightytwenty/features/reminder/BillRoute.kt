package uz.behzod.eightytwenty.features.reminder

sealed interface BillRoute {
    object AddBillRoute: BillRoute
}