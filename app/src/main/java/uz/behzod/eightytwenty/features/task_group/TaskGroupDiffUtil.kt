package uz.behzod.eightytwenty.features.task_group

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

object TaskGroupDiffUtil: DiffUtil.ItemCallback<TaskCatalogEntity>() {

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