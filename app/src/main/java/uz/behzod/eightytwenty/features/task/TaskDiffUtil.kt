package uz.behzod.eightytwenty.features.task

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.data.local.entities.TaskEntity

object TaskDiffUtil: DiffUtil.ItemCallback<TaskEntity>() {

    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }
}