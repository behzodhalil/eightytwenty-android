package uz.behzod.eightytwenty.features.task_group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.databinding.ViewHolderCatalogBinding

class TaskCatalogAdapter :
    ListAdapter<TaskCatalogEntity, TaskCatalogAdapter.TaskCatalogViewHolder>(TaskGroupDiffUtil) {

    inner class TaskCatalogViewHolder(
        val binding: ViewHolderCatalogBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskCatalogViewHolder {
        val binding = ViewHolderCatalogBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return TaskCatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskCatalogViewHolder, position: Int) {
        holder.apply {
            currentList[position].let {
                binding.apply {
                    tvCategoryName.text = it.name
                }
            }
        }
    }
}