package uz.behzod.eightytwenty.utils.manager

interface BroadcastManager {
    fun transferToLocal(status: String, data: String? = null)
}