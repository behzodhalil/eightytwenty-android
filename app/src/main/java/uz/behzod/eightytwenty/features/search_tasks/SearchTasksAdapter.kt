package uz.behzod.eightytwenty.features.search_tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.databinding.ViewHolderTaskBinding

class SearchTasksAdapter : ListAdapter<TaskEntity, SearchTasksAdapter.SearchTaskViewHolder>(SearchTaskDiffUtil) {

    inner class SearchTaskViewHolder(
        val binding: ViewHolderTaskBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTaskViewHolder {
        val binding =
            ViewHolderTaskBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )

        return SearchTaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchTaskViewHolder, position: Int) {
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