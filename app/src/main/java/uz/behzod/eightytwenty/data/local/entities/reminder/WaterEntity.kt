package uz.behzod.eightytwenty.data.local.entities.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

@Entity(tableName = "water_table")
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
    val timestamp: String = String.Empty,
    /**
     * It represents the id of water.
     */
    @ColumnInfo(name = "water_uid")
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero
)
