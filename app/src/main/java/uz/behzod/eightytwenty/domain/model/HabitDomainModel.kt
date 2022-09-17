package uz.behzod.eightytwenty.domain.model

import uz.behzod.eightytwenty.data.local.entities.Frequency
import uz.behzod.eightytwenty.data.local.entities.PerDayGoalType
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import uz.behzod.eightytwenty.utils.view.Colors
import java.util.*


data class HabitDomainModel(
    val title: String = String.Empty,
    val description: String = String.Empty,
    val perDayGoalCount: Long = Long.Zero,
    val endGoalCount: Long = Long.Zero,
    val perDayGoalType: PerDayGoalType = PerDayGoalType.ONCE,
    val frequency: Frequency = Frequency.DAILY,
    val timestamp: String = String.Empty,
    val color: Int = Colors.list[0],
    val isComplete: Boolean = false,
    val uid: Long = Long.Zero
)
