package uz.behzod.eightytwenty.features.search_tasks

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.data.local.entities.TaskEntity

/**
 *  SearchAdapter is called for using compare two items.
 */

object SearchTaskDiffUtil: DiffUtil.ItemCallback<TaskEntity>() {

    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }
}