package uz.behzod.eightytwenty.features.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.databinding.ViewHolderNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

class NoteAdapter(private val onClickListener: (data: NoteRelation) -> Unit) :
    ListAdapter<NoteRelation, NoteAdapter.NoteViewHolder>(COMPARATOR) {

    inner class NoteViewHolder(val binding: ViewHolderNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteRelation) {
            binding.apply {
                tvTitle.text = item.note.title
                tvDesc.text = item.note.description
                tvDate.text = item.note.timestamp.toString()
                if (item.images.isNotEmpty()){
                    val image = item.images.first()
                    ivImage.load(image.uri)
                }
            }
        }
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<NoteRelation>() {
            override fun areItemsTheSame(oldItem: NoteRelation, newItem: NoteRelation): Boolean {
                return oldItem.note.id == newItem.note.id
            }

            override fun areContentsTheSame(oldItem: NoteRelation, newItem: NoteRelation): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ViewHolderNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.apply {
            currentList[position].let { data ->
                bind(data)
                binding.root.setOnClickListener {
                    onClickListener(data)
                }
            }
        }
    }
}