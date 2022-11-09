package uz.behzod.eightytwenty.data.local.entities.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

@Entity(tableName = "pill_table")
data class PillEntity(
    /**
     * It represents the duration of pill
     */
    @ColumnInfo(name = "pill_duration")
    val duration: String = String.Empty,
    /**
     * It represents the frequency of pill.
     * [Daily, Weekly,Monthly]
     */
    @ColumnInfo(name = "pill_frequency")
    val frequency: String = String.Empty,
    /**
     * It represents the timestamp of pill.
     */
    @ColumnInfo(name = "pill_timestamp")
    val timestamp: String = String.Empty,
    /**
     * It represents the dose of pill.
     */
    @ColumnInfo(name = "pill_dose")
    val dose: Long = Long.Zero,
    /**
     * It represents the form of pill.
     */
    @ColumnInfo(name = "pill_form")
    val form: String = String.Empty,
    /**
     * It represents the name of pill.
     */
    @ColumnInfo(name = "pill_name")
    val name: String = String.Empty,
    /**
     * It represents the id of pill.
     */
    @ColumnInfo(name = "pill_uid")
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero
)
