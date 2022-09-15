package uz.behzod.eightytwenty.features.habit

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.domain.model.HabitDomainModel

object HabitDiffUtil : DiffUtil.ItemCallback<HabitDomainModel>() {

    override fun areItemsTheSame(oldItem: HabitDomainModel, newItem: HabitDomainModel): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: HabitDomainModel, newItem: HabitDomainModel): Boolean {
        return oldItem == newItem
    }
}