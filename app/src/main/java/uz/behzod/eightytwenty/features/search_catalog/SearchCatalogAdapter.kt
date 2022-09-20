package uz.behzod.eightytwenty.features.search_catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.databinding.ViewHolderCatalogBinding


class SearchCatalogAdapter :
    ListAdapter<TaskCatalogEntity, SearchCatalogAdapter.SearchCatalogViewHolder>(
        SearchCatalogDiffUtil
    ) {

    inner class SearchCatalogViewHolder(
        val binding: ViewHolderCatalogBinding
    ) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCatalogViewHolder {
        val binding = ViewHolderCatalogBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return SearchCatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchCatalogViewHolder, position: Int) {
        holder.apply {
            currentList[position].let { data ->
                binding.apply {
                    tvCategoryName.text = data.name
                }
            }
        }
    }
}