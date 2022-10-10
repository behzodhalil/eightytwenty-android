package uz.behzod.eightytwenty.features.habit_recommend

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

object HabitRecommendDiffUtil: DiffUtil.ItemCallback<HabitRecommendDomainModel>() {

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