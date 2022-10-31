package uz.behzod.eightytwenty.features.new_note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.databinding.ViewHolderCategoryNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel

private typealias OnClickListener = (data: NoteCategoryDomainModel) -> Unit

class NoteGroupPickerAdapter(private val clickListener: OnClickListener) :
    ListAdapter<NoteCategoryDomainModel, NoteGroupPickerAdapter.NoteGroupPickerViewHolder>(
        NoteGroupPickerDiffUtil
    ) {
    inner class NoteGroupPickerViewHolder(val binding: ViewHolderCategoryNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(group: NoteCategoryDomainModel) {
            binding.tvCategoryName.text = group.name
            binding.tvCategoryNoteCount.text = group.count.toString()
            binding.root.setOnClickListener {
                clickListener(group)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteGroupPickerViewHolder {
        val binding = ViewHolderCategoryNoteBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return NoteGroupPickerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteGroupPickerViewHolder, position: Int) {
        holder.apply {
            bind(currentList[position])
        }
    }
}