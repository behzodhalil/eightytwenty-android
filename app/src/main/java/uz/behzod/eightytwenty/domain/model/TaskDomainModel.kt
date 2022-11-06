package uz.behzod.eightytwenty.domain.model

import uz.behzod.eightytwenty.data.local.entities.Frequency
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

data class TaskDomainModel(
    val title: String = String.Empty,
    val dueDate: String = String.Empty,
    val frequency: Frequency = Frequency.DAILY,
    val category: String = String.Empty,
    val note: NoteEntity? = null,
    val timestamp: String = String.Empty,
    val deadline: String = String.Empty,
    val isComplete: Boolean = false,
    val isTrashed: Boolean = false,
    val catalogName: String = String.Empty,
    val catalogUid: Long = Long.Zero,
    val uid: Long = Long.Zero,
)
