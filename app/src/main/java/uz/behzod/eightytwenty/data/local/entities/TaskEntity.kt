package uz.behzod.eightytwenty.data.local.entities

import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero

data class TaskEntity(
    val uid: Long = Long.Zero,
    val title: String = String.Empty
)
