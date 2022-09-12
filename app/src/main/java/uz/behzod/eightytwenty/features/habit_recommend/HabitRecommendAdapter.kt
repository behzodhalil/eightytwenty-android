package uz.behzod.eightytwenty.features.habit_recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.databinding.ViewHolderRecommendHabitBinding
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

class HabitRecommendAdapter(private val onClickListener: (data: HabitRecommendDomainModel) -> Unit) :
    ListAdapter<HabitRecommendDomainModel, HabitRecommendAdapter.HabitRecommendViewHolder>(
        COMPARATOR
    ) {

    inner class HabitRecommendViewHolder(
        val binding: ViewHolderRecommendHabitBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<HabitRecommendDomainModel>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitRecommendViewHolder {
        val binding = ViewHolderRecommendHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitRecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitRecommendViewHolder, position: Int) {
        holder.apply {
            currentList[position].let { data ->
                binding.apply {
                    tvHabitTitle.text = data.title
                    tvHabitCount.text = data.perDayGoalCount.toString()
                    root.setOnClickListener {
                        onClickListener(data)
                    }
                }
            }
        }
    }
}