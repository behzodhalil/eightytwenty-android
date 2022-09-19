package uz.behzod.eightytwenty.features.new_task

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

object CatalogDiffUtil : DiffUtil.ItemCallback<TaskCatalogEntity>() {

    override fun areItemsTheSame(oldItem: TaskCatalogEntity, newItem: TaskCatalogEntity): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(
        oldItem: TaskCatalogEntity,
        newItem: TaskCatalogEntity
    ): Boolean {
        return oldItem == newItem
    }
}