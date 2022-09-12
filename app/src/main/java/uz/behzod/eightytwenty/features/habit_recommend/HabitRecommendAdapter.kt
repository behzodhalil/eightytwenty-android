package uz.behzod.eightytwenty.features.habit_recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.PerDayGoalType
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

                    when (data.perDayGoalType) {
                        PerDayGoalType.ONCE -> {
                            if (data.perDayGoalCount == 1L) {
                                tvHabitCount.text = "${data.perDayGoalCount} Раз"
                            } else {
                                tvHabitCount.text = "${data.perDayGoalCount} Раза"
                            }
                        }
                        PerDayGoalType.HOUR -> {
                            if (data.perDayGoalCount == 1L) {
                                tvHabitCount.text = "${data.perDayGoalCount} Час"
                            } else {
                                tvHabitCount.text = "${data.perDayGoalCount} Часа"
                            }

                        }
                        PerDayGoalType.MINUTES -> {
                            if (data.perDayGoalCount == 1L) {
                                tvHabitCount.text = "${data.perDayGoalCount} Минута"
                            } else {
                                tvHabitCount.text = "${data.perDayGoalCount} Минуты"
                            }
                        }
                        PerDayGoalType.PAGES -> {
                            if (data.perDayGoalCount == 1L) {
                                tvHabitCount.text = "${data.perDayGoalCount} Страница"
                            } else {
                                tvHabitCount.text = "${data.perDayGoalCount} Страницы"
                            }

                        }
                        PerDayGoalType.CUP -> {
                            if (data.perDayGoalCount == 1L) {
                                tvHabitCount.text = "${data.perDayGoalCount} Стакан"
                            } else {
                                tvHabitCount.text = "${data.perDayGoalCount} Стаканы"
                            }
                        }
                    }

                    when(data.color) {
                        "Green" -> {
                            tvHabitTitle.setTextColor(
                                ContextCompat.getColor(
                                    tvHabitTitle.context,
                                    R.color.paris_green
                                )
                            )

                            tvHabitCount.setTextColor(
                                ContextCompat.getColor(
                                    tvHabitCount.context,
                                    R.color.paris_green
                                )
                            )
                        }
                        "Light Green" -> {
                            tvHabitTitle.setTextColor(
                                ContextCompat.getColor(
                                    tvHabitTitle.context,
                                    R.color.light_badland
                                )
                            )
                            tvHabitCount.setTextColor(
                                ContextCompat.getColor(
                                    tvHabitCount.context,
                                    R.color.light_badland
                                )
                            )
                        }
                        "Blue" -> {
                            tvHabitTitle.setTextColor(
                                ContextCompat.getColor(
                                    tvHabitTitle.context,
                                    R.color.gloomy_purple
                                )
                            )
                            tvHabitCount.setTextColor(
                                ContextCompat.getColor(
                                    tvHabitCount.context,
                                    R.color.gloomy_purple
                                )
                            )
                        }
                    }
                    root.setOnClickListener {
                        onClickListener(data)
                    }
                }
            }
        }
    }
}