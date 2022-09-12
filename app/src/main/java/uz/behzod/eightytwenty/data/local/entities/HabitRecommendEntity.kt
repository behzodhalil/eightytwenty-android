package uz.behzod.eightytwenty.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.data.local.entities.HabitRecommendEntity.Companion.TABLE_NAME
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero

@Entity(
    tableName = TABLE_NAME
)
data class HabitRecommendEntity(
    @ColumnInfo(name = TITLE)
    val title: String = String.Empty,

    @ColumnInfo(name = DESCRIPTION)
    val description: String = String.Empty,

    @ColumnInfo(name = PER_DAY_GOAL_COUNT)
    val perDayGoalCount: Long = Long.Zero,

    @ColumnInfo(name = END_GOAL_COUNT)
    val endGoalCount: Long = Long.Zero,

    @ColumnInfo(name = GOAL_TYPE )
    val perDayGoalType: PerDayGoalType = PerDayGoalType.ONCE,

    @ColumnInfo(name = FREQUENCY)
    val frequency: Frequency = Frequency.DAILY,

    @ColumnInfo(name = TIMESTAMP)
    val timestamp: Long = Long.Zero,

    @ColumnInfo(name = COLOR)
    val color: String = String.Empty,

    @ColumnInfo(name = IS_COMPLETE)
    val isComplete: Boolean = false,

    @ColumnInfo(name = CATEGORY)
    val category: String = String.Empty,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UID)
    val uid: Long = Long.Zero
) {
    companion object {
        const val TABLE_NAME = "habit_recommend_table"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val PER_DAY_GOAL_COUNT = "per_day_goal_count"
        private const val END_GOAL_COUNT = "end_goal_count"
        private const val FREQUENCY = "frequency"
        private const val TIMESTAMP = "timestamp"
        private const val GOAL_TYPE = "per_day_goal_type"
        private const val COLOR = "color"
        private const val IS_COMPLETE = "is_complete"
        private const val CATEGORY = "category"
        private const val UID = "uid"
    }
}

fun HabitRecommendEntity.asDomain(): HabitRecommendDomainModel {
    return HabitRecommendDomainModel(
        this.title,
        this.description,
        this.perDayGoalCount,
        this.perDayGoalType,
        this.endGoalCount,
        this.frequency,
        this.timestamp,
        this.color,
        this.isComplete,
        this.category,
        this.uid
    )
}

fun HabitRecommendDomainModel.asEntity(): HabitRecommendEntity {
    return HabitRecommendEntity(
        this.title,
        this.description,
        this.perDayGoalCount,
        this.endGoalCount,
        this.perDayGoalType,
        this.frequency,
        this.timestamp,
        this.color,
        this.isComplete,
        this.category,
        this.uid
    )
}
