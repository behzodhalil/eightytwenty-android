package uz.behzod.eightytwenty.features.category_note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.databinding.ViewHolderCategoryNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel

class CategoryNoteAdapter :
    ListAdapter<NoteCategoryDomainModel, CategoryNoteAdapter.CategoryNoteViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<NoteCategoryDomainModel>() {
            override fun areItemsTheSame(
                oldItem: NoteCategoryDomainModel,
                newItem: NoteCategoryDomainModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NoteCategoryDomainModel,
                newItem: NoteCategoryDomainModel
            ): Boolean {
                return oldItem.name == newItem.name && oldItem.count == newItem.count
            }
        }
    }

    inner class CategoryNoteViewHolder(val binding: ViewHolderCategoryNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryNoteViewHolder {
        val binding = ViewHolderCategoryNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryNoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryNoteViewHolder, position: Int) {
        holder.apply {
            currentList[position].let {
                binding.apply {
                    tvCategoryName.text = it.name
                    tvCategoryNoteCount.text = it.count.toString()
                }
            }
        }
    }
}