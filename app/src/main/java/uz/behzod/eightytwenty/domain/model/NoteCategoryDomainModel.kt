package uz.behzod.eightytwenty.domain.model

import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

data class NoteCategoryDomainModel(
    val uid: Long = Long.Zero,
    val name: String = String.Empty,
    val count: Int = Int.Zero
)
