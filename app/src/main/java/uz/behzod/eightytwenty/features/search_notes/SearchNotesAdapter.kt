package uz.behzod.eightytwenty.features.search_notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.databinding.ViewHolderNoteBinding

class SearchNotesAdapter :
    ListAdapter<NoteEntity, SearchNotesAdapter.SearchNotesViewHolder>(SearchNotesDiffUtil) {

    inner class SearchNotesViewHolder(
        val binding: ViewHolderNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)


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
