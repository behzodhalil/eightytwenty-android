package uz.behzod.eightytwenty.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.data.local.entities.TaskGroupEntity.Companion.TASK_GROUP_TABLE_NAME
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

@Entity(
    tableName = TASK_GROUP_TABLE_NAME
)
data class TaskGroupEntity(
    @ColumnInfo(name = NAME)
    val name: String = String.Empty,

    @ColumnInfo(name = UID)
    @PrimaryKey(autoGenerate = true)
    val uid: Long = Long.Zero
) {
    companion object {
        const val TASK_GROUP_TABLE_NAME = "task_group_table"
        private const val NAME = "task_group_name"
        private const val UID = "task_group_uid"
    }
}