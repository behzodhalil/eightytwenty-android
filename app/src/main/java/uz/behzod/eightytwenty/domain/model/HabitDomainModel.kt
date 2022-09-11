package uz.behzod.eightytwenty.domain.model

import uz.behzod.eightytwenty.data.local.entities.Frequency
import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero


data class HabitDomainModel(
    val title: String = String.Empty,
    val description: String = String.Empty,
    val perDayGoalCount: Long = Long.Zero,
    val endGoalCount: Long = Long.Zero,
    val frequency: Frequency = Frequency.DAILY,
    val timestamp: Long = Long.Zero,
    val color: String = String.Empty,
    val isComplete: Boolean = false,
    val uid: Long = Long.Zero
)
