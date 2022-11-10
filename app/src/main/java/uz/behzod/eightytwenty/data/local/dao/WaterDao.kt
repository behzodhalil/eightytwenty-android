package uz.behzod.eightytwenty.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

@Dao
interface WaterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(water: WaterEntity): Long
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(water: WaterEntity)
    @Delete
    suspend fun delete(water: WaterEntity)
    @Query("SELECT * FROM water_table WHERE water_timestamp >:timestamp ORDER BY water_timestamp DESC")
    fun fetchWaterAfterTimestamp(timestamp: Long): Flow<List<WaterEntity>>
    @Query("SELECT * FROM water_table ORDER BY water_uid")
    fun fetchWaters(): Flow<List<WaterEntity>>
}