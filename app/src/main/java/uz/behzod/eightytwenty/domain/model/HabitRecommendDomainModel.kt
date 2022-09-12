package uz.behzod.eightytwenty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.behzod.eightytwenty.data.local.entities.Frequency
import uz.behzod.eightytwenty.data.local.entities.PerDayGoalType
import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero

@Parcelize
data class HabitRecommendDomainModel(
    val title: String = String.Empty,
    val description: String = String.Empty,
    val perDayGoalCount: Long = Long.Zero,
    val perDayGoalType : PerDayGoalType,
    val endGoalCount: Long = Long.Zero,
    val frequency: Frequency = Frequency.DAILY,
    val timestamp: Long = Long.Zero,
    val color: String = String.Empty,
    val isComplete: Boolean = false,
    val category: String = String.Empty,
    val uid: Long = Long.Zero
): Parcelable
