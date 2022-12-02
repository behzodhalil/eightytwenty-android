package uz.behzod.eightytwenty.data.local.entities.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.constants.DatabaseConstants
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

@Entity(tableName = DatabaseConstants.Water.WATER_TABLE_NAME)
data class WaterEntity(
    /**
     * It represents the quantity of water.
     * [100,200,400,500,600,800,1000]
     */
    @ColumnInfo(name = "water_quantity")
    val quantity: Long = Long.Zero,
    /**
     * It represents the timestamp of water.
     */
    @ColumnInfo(name = "water_timestamp")
    val timestamp: ZonedDateTime? = null,

    /**
     * It represents the frequency of water.
     */
    @ColumnInfo(name = "water_frequency")
    val frequency: String = String.Empty,

    /**
     * It represents the reminder of time.
     */
    @ColumnInfo(name = "water_reminder_time")
    val reminderTime: ZonedDateTime? = null,
    /**
     * It represents the id of water.
     */
    @ColumnInfo(name = "water_uid")
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero,
)
