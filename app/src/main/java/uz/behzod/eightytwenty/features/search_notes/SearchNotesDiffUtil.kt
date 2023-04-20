package uz.behzod.eightytwenty.features.search_notes

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

object SearchNotesDiffUtil: DiffUtil.ItemCallback<NoteEntity>() {

    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem == newItem
    }
}
