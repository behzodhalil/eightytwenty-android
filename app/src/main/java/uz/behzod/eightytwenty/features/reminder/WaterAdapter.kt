package uz.behzod.eightytwenty.features.reminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity
import uz.behzod.eightytwenty.databinding.ViewHolderWaterItemBinding

class WaterAdapter: ListAdapter<WaterEntity,WaterAdapter.WaterViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<WaterEntity>() {
            override fun areItemsTheSame(oldItem: WaterEntity, newItem: WaterEntity): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: WaterEntity, newItem: WaterEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    inner class WaterViewHolder(
        val binding: ViewHolderWaterItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(water: WaterEntity) {
            binding.tvWaterQuantity.text = water.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterViewHolder {
        val binding = ViewHolderWaterItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return WaterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WaterViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}