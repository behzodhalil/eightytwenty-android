package uz.behzod.eightytwenty.features.new_task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.databinding.DialogCatalogBinding
import uz.behzod.eightytwenty.databinding.ViewHolderCatalogBinding

class CatalogAdapter(private val onClickListener: (TaskCatalogEntity) -> Unit) :
    ListAdapter<TaskCatalogEntity, CatalogAdapter.CatalogViewHolder>(CatalogDiffUtil) {

    inner class CatalogViewHolder(val binding: ViewHolderCatalogBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val binding = ViewHolderCatalogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.apply {
            currentList[position].let { data ->
                binding.apply {
                    tvCategoryName.text = data.name
                    root.setOnClickListener {
                        onClickListener(data)
                    }
                }
            }
        }
    }
}