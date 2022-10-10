package uz.behzod.eightytwenty.features.new_note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.databinding.ViewHolderMultipleImageItemBinding
import uz.behzod.eightytwenty.databinding.ViewHolderSingleImageItemBinding
import uz.behzod.eightytwenty.utils.view.bindBitmap

class ImageAdapter : ListAdapter<NoteImageEntity, ImageViewHolder>(ImageDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return when (viewType) {
            R.layout.view_holder_single_image_item -> {
                val binding = ViewHolderSingleImageItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ImageViewHolder.SingleImageViewHolder(binding)
            }
            R.layout.view_holder_multiple_image_item -> {
                val binding = ViewHolderMultipleImageItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent,
                    false
                )
                ImageViewHolder.MultipleImageViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type of View Holder")
        }
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = currentList[position]
        when (holder) {
            is ImageViewHolder.MultipleImageViewHolder -> {
                holder.bind(item)
            }
            is ImageViewHolder.SingleImageViewHolder -> {
                holder.bind(item)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            R.layout.view_holder_single_image_item
        } else {
            R.layout.view_holder_multiple_image_item
        }
    }

}

sealed class ImageViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    class SingleImageViewHolder(
        private val binding: ViewHolderSingleImageItemBinding
    ) : ImageViewHolder(binding) {
        fun bind(image: NoteImageEntity) {
            binding.ivSingleItem.bindBitmap(image.uri, 150, 150)
        }
    }

    class MultipleImageViewHolder(
        private val binding: ViewHolderMultipleImageItemBinding
    ) : ImageViewHolder(binding) {
        fun bind(image: NoteImageEntity) {
            binding.ivMultipleItem.bindBitmap(image.uri, 100, 100)
        }
    }
}
