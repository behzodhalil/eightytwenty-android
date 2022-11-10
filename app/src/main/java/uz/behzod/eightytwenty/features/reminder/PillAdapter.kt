package uz.behzod.eightytwenty.features.reminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity
import uz.behzod.eightytwenty.databinding.ViewHolderPillItemBinding

class PillAdapter: ListAdapter<PillEntity,PillAdapter.PillViewHolder>(diffUtil){

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<PillEntity>() {
            override fun areItemsTheSame(oldItem: PillEntity, newItem: PillEntity): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: PillEntity, newItem: PillEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class PillViewHolder(
        val binding: ViewHolderPillItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pill: PillEntity) {
            binding.tvPillName.text = pill.name
            binding.tvPillDose.text = pill.dose.toString()
            binding.tvPillTimestamp.text = pill.timestamp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillViewHolder {
        val binding = ViewHolderPillItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return PillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PillViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}