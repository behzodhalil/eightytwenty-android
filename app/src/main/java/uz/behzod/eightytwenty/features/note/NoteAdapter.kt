package uz.behzod.eightytwenty.features.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.databinding.ViewHolderNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

class NoteAdapter (private val onClickListener: (data: NoteDomainModel) -> Unit): ListAdapter<NoteDomainModel,NoteAdapter.NoteViewHolder>(COMPARATOR) {

    inner class NoteViewHolder(val binding: ViewHolderNoteBinding) :
        RecyclerView.ViewHolder(binding.root)


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<NoteDomainModel>() {
            override fun areItemsTheSame(oldItem: NoteDomainModel, newItem: NoteDomainModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NoteDomainModel, newItem: NoteDomainModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ViewHolderNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.apply {
            currentList[position].let { data ->
                binding.apply {
                    tvTitle.text = data.title
                    tvDesc.text = data.description
                    tvDate.text = data.timestamp.toString()
                }
                binding.root.setOnClickListener {
                    onClickListener(data)
                }
            }
        }
    }
}