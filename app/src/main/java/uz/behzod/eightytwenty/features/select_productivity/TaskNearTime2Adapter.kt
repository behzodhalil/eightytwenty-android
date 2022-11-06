package uz.behzod.eightytwenty.features.select_productivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import uz.behzod.eightytwenty.databinding.HeaderProductivityItemBinding
import uz.behzod.eightytwenty.databinding.RowTaskProductivityItemBinding
import uz.behzod.eightytwenty.domain.model.TaskDomainModel

class TaskNearTime2Adapter :
    ListAdapter<TaskDomainModel, ProductivityViewHolder>(diffUtil) {

    companion object {
        private const val HEADER_ITEM = 0
        private const val ROW_ITEM = 1

        private val diffUtil = object : DiffUtil.ItemCallback<TaskDomainModel>() {
            override fun areItemsTheSame(
                oldItem: TaskDomainModel,
                newItem: TaskDomainModel,
            ): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(
                oldItem: TaskDomainModel,
                newItem: TaskDomainModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_ITEM else ROW_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductivityViewHolder {
        return when (viewType) {
            HEADER_ITEM -> {
                val binding = HeaderProductivityItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent,
                    false
                )
                TaskNearTimeHeaderViewHolder(binding)
            }
            ROW_ITEM -> {
                val binding = RowTaskProductivityItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent,
                    false
                )
                TaskNearTimeRowViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type of View Holder")
        }
    }

    override fun onBindViewHolder(holder: ProductivityViewHolder, position: Int) {
        val item = currentList[position]
        when (holder) {
            is TaskNearTimeHeaderViewHolder -> {
                holder.bind("Добавлено недавно")
            }
            is TaskNearTimeRowViewHolder -> {
                holder.bind(item)
            }
        }
    }

    abstract class VH(VB: ViewBinding):RecyclerView.ViewHolder(VB.root)

    class HeaderVH(val binding:  HeaderProductivityItemBinding):VH(binding) {
        fun bind(title: String) {
            binding.tvHeader.text = title
        }
    }

    class RowVH(val binding: RowTaskProductivityItemBinding):VH(binding) {
        fun bind(task: TaskDomainModel) {
            binding.tvTaskTitle.text = task.title
            binding.tvTaskTimestamp.text = task.timestamp
        }
    }


    class TaskNearTimeHeaderViewHolder(
        val viewBinding: HeaderProductivityItemBinding,
    ) : ProductivityViewHolder(viewBinding) {
        fun bind(title: String) {
            viewBinding.tvHeader.text = title
        }
    }

    class TaskNearTimeRowViewHolder(
        val viewBinding: RowTaskProductivityItemBinding,
    ) : ProductivityViewHolder(viewBinding) {
        fun bind(task: TaskDomainModel) {
            viewBinding.tvTaskTitle.text = task.title
            viewBinding.tvTaskTimestamp.text = task.timestamp
        }
    }


}