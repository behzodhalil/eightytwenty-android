package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity

@Dao
interface NoteCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: NoteCategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(category: NoteCategoryEntity)

    @Delete
    suspend fun delete(category: NoteCategoryEntity)

    @Query("UPDATE note_category_table SET note_count = note_count + 1 WHERE note_category_id = :noteCategoryId")
    suspend fun incrementNoteCount(noteCategoryId: Long)

    @Query("UPDATE note_category_table SET note_count = note_count-1 WHERE note_category_id = :noteCategoryId")
    suspend fun decrementNoteCount(noteCategoryId: Long)

    @Query("SELECT * FROM note_category_table ORDER BY note_category_name")
    suspend fun fetchAllCategories(): List<NoteCategoryEntity>

    @Query("SELECT EXISTS (SELECT note_category_id FROM note_category_table WHERE note_category_id = :noteCategoryId LIMIT 1)")
    suspend fun fetchIfCategoryIdExists(noteCategoryId: Int): Boolean
}