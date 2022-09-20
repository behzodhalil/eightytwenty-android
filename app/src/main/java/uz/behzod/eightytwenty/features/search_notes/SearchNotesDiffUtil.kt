package uz.behzod.eightytwenty.features.search_notes

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

object SearchNotesDiffUtil: DiffUtil.ItemCallback<NoteDomainModel>() {

    override fun areItemsTheSame(oldItem: NoteDomainModel, newItem: NoteDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteDomainModel, newItem: NoteDomainModel): Boolean {
        return oldItem == newItem
    }
}