package uz.behzod.eightytwenty.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_table"
)
data class UserEntity(
    @ColumnInfo(name = USER_NAME)
    val name: String,
    @ColumnInfo(name = USER_UID)
    @PrimaryKey(autoGenerate = false)
    val uid: Long,
) {
    companion object {
        private const val USER_NAME = "user_name"
        private const val USER_UID = "user_uid"
    }
}
