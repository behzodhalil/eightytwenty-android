package uz.behzod.eightytwenty.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero

@Entity(
    tableName = "note_category_table",
    indices = [Index(value = ["note_category_name"], unique = true)]
)
data class NoteCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Schema.ID)
    val id: Long = Long.Zero,

    @ColumnInfo(name = Schema.NAME)
    val name: String = String.Empty,

    @ColumnInfo(name = Schema.COUNT)
    val count: Int = Int.Zero
) {
    private object Schema {
        const val ID = "note_category_id"
        const val NAME = "note_category_name"
        const val COUNT = "note_count"
    }
}
