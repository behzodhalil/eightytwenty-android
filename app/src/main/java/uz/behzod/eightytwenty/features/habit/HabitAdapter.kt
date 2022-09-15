package uz.behzod.eightytwenty.features.habit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.databinding.ViewHolderHabitBinding
import uz.behzod.eightytwenty.domain.model.HabitDomainModel

class HabitAdapter(private val onClickListener: (data: HabitDomainModel) -> Unit) :
    ListAdapter<HabitDomainModel, HabitAdapter.HabitViewHolder>(HabitDiffUtil) {

    inner class HabitViewHolder(val binding: ViewHolderHabitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ViewHolderHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
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