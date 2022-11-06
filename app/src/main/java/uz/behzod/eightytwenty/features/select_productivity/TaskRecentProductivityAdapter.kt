package uz.behzod.eightytwenty.features.select_productivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.databinding.HeaderProductivityItemBinding
import uz.behzod.eightytwenty.databinding.RowTaskProductivityItemBinding
import uz.behzod.eightytwenty.domain.model.TaskDomainModel
import uz.behzod.eightytwenty.utils.extension.printDebug

class TaskRecentProductivityAdapter :
    ListAdapter<TaskEntity, RecyclerView.ViewHolder>(diffUtil) {

    companion object {
        private const val HEADER_INDEX = 0
        private const val ROW_INDEX = 1

        private val diffUtil = object : DiffUtil.ItemCallback<TaskEntity>() {
            override fun areItemsTheSame(
                oldItem: TaskEntity,
                newItem: TaskEntity,
            ): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(
                oldItem: TaskEntity,
                newItem: TaskEntity,
            ): Boolean {
                return oldItem.timestamp == newItem.timestamp &&
                        oldItem.title == newItem.title
            }
        }
    }

    private inner class HeaderViewHolder(val binding: HeaderProductivityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String) {
            binding.tvHeader.text = value
        }
    }

    private inner class RowViewHolder(val binding: RowTaskProductivityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskEntity) {
            binding.tvTaskTitle.text = task.title
            binding.tvTaskTimestamp.text = task.timestamp.toString()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_INDEX else ROW_INDEX
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_INDEX -> {
                printDebug {
                    "[TaskRecentAdapter]:" + "View type body"
                }
                val binding =
                    HeaderProductivityItemBinding.inflate(LayoutInflater.from(parent.context),
                        parent,
                        false)
                printDebug {
                    "[TaskRecentAdapter]:" + "Initialized"
                }
                HeaderViewHolder(binding)

            }
            ROW_INDEX -> {
                val binding =
                    RowTaskProductivityItemBinding.inflate(LayoutInflater.from(parent.context),
                        parent,
                        false)
                RowViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type of View Holder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        when (holder) {
            is HeaderViewHolder -> {
                printDebug {
                    "[TaskRecentAdapter]:" + "holder.bind"
                }
                holder.bind("Добавлено недавно")
            }
            is RowViewHolder -> {
                holder.bind(item)
            }
        }
    }
}