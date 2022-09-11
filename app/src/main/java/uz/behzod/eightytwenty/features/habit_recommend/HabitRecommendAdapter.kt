package uz.behzod.eightytwenty.features.habit_recommend

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.databinding.ViewHolderRecommendHabitBinding
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

class HabitRecommendAdapter {

    inner class HabitRecommendViewHolder(
        binding: ViewHolderRecommendHabitBinding
    ): RecyclerView.ViewHolder(binding.root)

    companion object {
        private val COMPARATOR = object: DiffUtil.ItemCallback<HabitRecommendDomainModel>() {
            override fun areItemsTheSame(
                oldItem: HabitRecommendDomainModel,
                newItem: HabitRecommendDomainModel
            ): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(
                oldItem: HabitRecommendDomainModel,
                newItem: HabitRecommendDomainModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}