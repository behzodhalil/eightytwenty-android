package uz.behzod.eightytwenty.data.local.entities.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.constants.DatabaseConstants.Pill
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

@Entity(tableName =Pill.PILL_TABLE_NAME)
data class PillEntity(
    /**
     * It represents the duration of pill
     */
    @ColumnInfo(name = Pill.PILL_DURATION)
    val duration: String = String.Empty,
    /**
     * It represents the frequency of pill.
     * [Daily, Weekly,Monthly]
     */
    @ColumnInfo(name = Pill.PILL_FREQUENCY)
    val frequency: String = String.Empty,
    /**
     * It represents the timestamp of pill.
     */
    @ColumnInfo(name = Pill.PILL_TIMESTAMP)
    val timestamp: String = String.Empty,
    /**
     * It represents the dose of pill.
     */
    @ColumnInfo(name = Pill.PILL_DOSE)
    val dose: Long = Long.Zero,
    /**
     * It represents the form of pill.
     */
    @ColumnInfo(name = Pill.PILL_FORM)
    val form: String = String.Empty,
    /**
     * It represents the name of pill.
     */
    @ColumnInfo(name = Pill.PILL_NAME)
    val name: String = String.Empty,
    /**
     * It represents the id of pill.
     */
    @ColumnInfo(name = Pill.PILL_UID)
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero
)
