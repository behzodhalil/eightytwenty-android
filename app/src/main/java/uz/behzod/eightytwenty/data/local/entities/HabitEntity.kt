package uz.behzod.eightytwenty.data.local.entities

import android.graphics.Color
import androidx.room.*
import uz.behzod.eightytwenty.data.local.entities.HabitEntity.Companion.HABIT_TABLE
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import uz.behzod.eightytwenty.utils.view.Colors
import java.util.HashMap

@Entity(
    tableName = HABIT_TABLE
)
data class HabitEntity(
    @ColumnInfo(name = TITLE)
    val title: String = String.Empty,

    @ColumnInfo(name = DESCRIPTION)
    val description: String = String.Empty,

    @ColumnInfo(name = PER_DAY_GOAL_COUNT)
    val perDayGoalCount: Long = Long.Zero,

    @ColumnInfo(name = END_GOAL_COUNT)
    val endGoalCount: Long = Long.Zero,

    @ColumnInfo(name = PER_DAY_GOAL_TYPE)
    val perDayGoalType: PerDayGoalType = PerDayGoalType.ONCE,

    @ColumnInfo(name = FREQUENCY)
    val frequency: Frequency = Frequency.DAILY,

    @ColumnInfo(name = TIMESTAMP)
    val timestamp: String = String.Empty,

    @ColumnInfo(name = COLOR)
    val color: Int = Colors.list[0],

    @ColumnInfo(name = IS_COMPLETE)
    val isComplete: Boolean = false,

    @ColumnInfo(name = UID, index = true)
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero
) {
    companion object {
        const val HABIT_TABLE = "habit_table"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val PER_DAY_GOAL_COUNT = "per_day_goal_count"
        private const val END_GOAL_COUNT = "end_goal_count"
        private const val PER_DAY_GOAL_TYPE = "per_day_goal_type"
        private const val FREQUENCY = "frequency"
        private const val TIMESTAMP = "timestamp"
        private const val COLOR = "color"
        private const val IS_COMPLETE = "is_complete"
        private const val UID = "uid"
    }

}

fun HabitEntity.asDomain(): HabitDomainModel {
    return HabitDomainModel(
        title = this.title,
        description = this.description,
        perDayGoalCount = this.perDayGoalCount,
        endGoalCount = this.endGoalCount,
        frequency = this.frequency,
        timestamp = this.timestamp,
        color = this.color,
        isComplete = this.isComplete,
        uid = this.uid
    )
}

fun HabitDomainModel.asEntity(): HabitEntity {
    return HabitEntity(
        title = this.title,
        description = this.description,
        perDayGoalCount = this.perDayGoalCount,
        endGoalCount = this.endGoalCount,
        frequency = this.frequency,
        timestamp = this.timestamp,
        color = this.color,
        isComplete = this.isComplete,
        uid = this.uid
    )
}

