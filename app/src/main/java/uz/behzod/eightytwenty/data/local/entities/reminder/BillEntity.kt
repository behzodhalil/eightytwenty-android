package uz.behzod.eightytwenty.data.local.entities.reminder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.constants.DatabaseConstants.Bill
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

@Entity(tableName = Bill.BILL_TABLE_NAME)
data class BillEntity(
    /**
     * It represents the amount of bill.
     */
    @ColumnInfo(name = Bill.BILL_AMOUNT)
    val amount: Long = Long.Zero,
    /**
     * It represents the type of bill.
     */
    @ColumnInfo(name = Bill.BILL_TYPE)
    val type: String = String.Empty,
    /**
     * It represents the date of bill.
     */
    @ColumnInfo(name = Bill.BILL_TIMESTAMP)
    val timestamp: ZonedDateTime? = null,
    /**
     * It represents the duration of bill.
     */
    @ColumnInfo(name = Bill.BILL_DURATION)
    val duration: Long = Long.Zero,
    /**
     * It represents the name of bill.
     */
    @ColumnInfo(name = Bill.BILL_NAME)
    val name: String = String.Empty,
    /**
     * It represents the id of bill.
     */
    @ColumnInfo(name = Bill.BILL_UID)
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero
)
