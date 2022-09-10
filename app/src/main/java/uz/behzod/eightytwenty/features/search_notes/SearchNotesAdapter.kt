package uz.behzod.eightytwenty.features.search_notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.databinding.ViewHolderNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

class SearchNotesAdapter : ListAdapter<NoteDomainModel, SearchNotesAdapter.SearchNotesViewHolder>(
    COMPARATOR
) {

    inner class SearchNotesViewHolder(
        val binding: ViewHolderNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<NoteDomainModel>() {
            override fun areItemsTheSame(
                oldItem: NoteDomainModel,
                newItem: NoteDomainModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NoteDomainModel,
                newItem: NoteDomainModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNotesViewHolder {
        val binding =
            ViewHolderNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchNotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchNotesViewHolder, position: Int) {
        holder.apply {
            currentList[position].let {
                binding.apply {
                    tvTitle.text = it.title
                    tvDesc.text = it.description
                    tvDate.text = it.timestamp.toString()
                }
            }
        }
    }
}