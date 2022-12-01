package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.CategoryAndNotes
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity

@Dao
interface NoteCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: NoteCategoryEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(category: NoteCategoryEntity)

    @Delete
    suspend fun delete(category: NoteCategoryEntity)

    @Query("UPDATE note_category_table SET note_count = note_count + 1 WHERE id = :noteCategoryId")
    suspend fun incrementNoteCount(noteCategoryId: Long)

    @Query("UPDATE note_category_table SET note_count = note_count-1 WHERE id = :noteCategoryId")
    suspend fun decrementNoteCount(noteCategoryId: Long)

    @Transaction
    @Query("SELECT * FROM note_category_table ORDER BY id")
    fun fetchAllCategories(): Flow<List<NoteCategoryEntity>>

    @Transaction
    @Query("SELECT * FROM note_category_table ORDER BY (id <> 1), note_category_name")
    fun fetchAllCategoriesWithNotes(): Flow<List<CategoryAndNotes>>

    @Query("SELECT EXISTS (SELECT id FROM note_category_table WHERE id = :noteCategoryId LIMIT 1)")
    suspend fun fetchIfCategoryIdExists(noteCategoryId: Int): Boolean
}
