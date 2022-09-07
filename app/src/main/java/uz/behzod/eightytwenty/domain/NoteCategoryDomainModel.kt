package uz.behzod.eightytwenty.domain

import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero

data class NoteCategoryDomainModel(
    val id: Long = Long.Zero,
    val name: String = String.Empty,
    val count: Int = Int.Zero
)
