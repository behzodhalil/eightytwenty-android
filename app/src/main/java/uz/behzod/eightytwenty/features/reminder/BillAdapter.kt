package uz.behzod.eightytwenty.features.reminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.databinding.ViewHolderBillItemBinding

class BillAdapter: ListAdapter<BillEntity,BillAdapter.BillViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<BillEntity>() {
            override fun areItemsTheSame(oldItem: BillEntity, newItem: BillEntity): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: BillEntity, newItem: BillEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class BillViewHolder(val binding: ViewHolderBillItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bill: BillEntity) {
            binding.tvBillAmount.text = bill.amount.toString()
            binding.tvBillName.text = bill.name
            binding.tvBillType.text = bill.type
            binding.tvBillPayDay.text = bill.duration.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val binding = ViewHolderBillItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return BillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}