package uz.behzod.eightytwenty.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity

@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bill: BillEntity):Long
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(bill: BillEntity)
    @Delete
    suspend fun delete(bill: BillEntity)
    @Query("SELECT * FROM bill_table ORDER BY bill_date DESC")
    fun fetchBills(): Flow<List<BillEntity>>
}