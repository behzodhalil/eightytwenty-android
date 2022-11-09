package uz.behzod.eightytwenty.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity

@Dao
interface PillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pill: PillEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(pill: PillEntity)
    @Delete
    suspend fun delete(pill: PillEntity)
    @Query("SELECT * FROM pill_table")
    fun fetchPills(): Flow<List<PillEntity>>
}