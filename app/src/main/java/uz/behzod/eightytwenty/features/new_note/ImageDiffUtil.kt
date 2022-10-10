package uz.behzod.eightytwenty.features.new_note

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

object ImageDiffUtil : DiffUtil.ItemCallback<NoteImageEntity>() {
    override fun areItemsTheSame(oldItem: NoteImageEntity, newItem: NoteImageEntity): Boolean {
        return oldItem.imageUid == newItem.imageUid
    }

    override fun areContentsTheSame(oldItem: NoteImageEntity, newItem: NoteImageEntity): Boolean {
        return oldItem == newItem
    }
}