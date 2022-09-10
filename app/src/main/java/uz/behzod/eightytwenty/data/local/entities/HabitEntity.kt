package uz.behzod.eightytwenty.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.data.local.entities.HabitEntity.Companion.HABIT_TABLE
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero

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

    @ColumnInfo(name = FREQUENCY)
    val frequency: String = String.Empty,

    @ColumnInfo(name = TIMESTAMP)
    val timestamp: Long = Long.Zero,

    @ColumnInfo(name = COLOR)
    val color: String = String.Empty,

    @ColumnInfo(name = UID)
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero
) {
    companion object {
        const val HABIT_TABLE = "habit_table"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val PER_DAY_GOAL_COUNT = "per_day_goal_count"
        private const val END_GOAL_COUNT = "end_goal_count"
        private const val FREQUENCY = "frequency"
        private const val TIMESTAMP = "timestamp"
        private const val COLOR = "color"
        private const val UID = "uid"
    }
}

fun HabitEntity.asDomain(): HabitDomainModel {
    return HabitDomainModel(
        this.title,
        this.description,
        this.perDayGoalCount,
        this.endGoalCount,
        this.frequency,
        this.timestamp,
        this.color,
        this.uid
    )
}


