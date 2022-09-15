package uz.behzod.eightytwenty.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

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
        const val ID = "id"
        const val NAME = "note_category_name"
        const val COUNT = "note_count"
    }
}

fun NoteCategoryEntity.asDomain(): NoteCategoryDomainModel {
    return NoteCategoryDomainModel(
        uid = id,
        name = name,
        count = count
    )
}

fun NoteCategoryDomainModel.asEntity() : NoteCategoryEntity {
    return NoteCategoryEntity(
        id = uid,
        name = name,
        count = count
    )
}

fun List<NoteCategoryEntity>.asListOfDomain(): List<NoteCategoryDomainModel> {
    return this.flatMap {
        listOf(it.asDomain())
    }
}

fun List<NoteCategoryDomainModel>.asListOfEntity(): List<NoteCategoryEntity> {
    return this.flatMap {
        listOf(it.asEntity())
    }
}


