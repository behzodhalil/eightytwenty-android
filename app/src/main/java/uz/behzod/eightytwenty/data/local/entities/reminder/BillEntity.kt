package uz.behzod.eightytwenty.data.local.entities.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

@Entity(tableName = "bill_table")
data class BillEntity(
    /**
     * It represents the amount of bill.
     */
    @ColumnInfo(name = "bill_amount")
    val amount: Long = Long.Zero,
    /**
     * It represents the type of bill.
     */
    @ColumnInfo(name = "bill_type")
    val type: String = String.Empty,
    /**
     * It represents the date of bill.
     */
    @ColumnInfo(name = "bill_date")
    val timestamp: ZonedDateTime? = null,
    /**
     * It represents the duration of bill.
     */
    @ColumnInfo(name = "bill_duration")
    val duration: Long = Long.Zero,
    /**
     * It represents the name of bill.
     */
    val name: String = String.Empty,
    /**
     * It represents the id of bill.
     */
    @ColumnInfo(name = "bill_uid")
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero
)
