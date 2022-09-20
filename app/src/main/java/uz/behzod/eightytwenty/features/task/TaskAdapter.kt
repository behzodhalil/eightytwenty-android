package uz.behzod.eightytwenty.features.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.databinding.ViewHolderTaskBinding

class TaskAdapter: ListAdapter<TaskEntity,TaskAdapter.TaskViewHolder>(TaskDiffUtil) {

    inner class TaskViewHolder(
        val binding: ViewHolderTaskBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ViewHolderTaskBinding.inflate(
            LayoutInflater.from(parent.context),parent,
            false
        )

        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.apply {
            currentList[position].let { data ->
                binding.apply {
                    tvTaskTitle.text = data.title
                    tvTaskTimestamp.text = data.timestamp.toString()
                }
            }
        }
    }
}