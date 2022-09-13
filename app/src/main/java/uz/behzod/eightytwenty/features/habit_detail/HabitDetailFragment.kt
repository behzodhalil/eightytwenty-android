package uz.behzod.eightytwenty.features.habit_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentHabitDetailBinding
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class HabitDetailFragment: Fragment(R.layout.fragment_habit_detail) {

    private val binding by viewBinding(FragmentHabitDetailBinding::bind)
    private val viewModel: HabitDetailViewModel by viewModels()
    private val args: HabitDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchHabitUid()
    }

    private fun fetchHabitUid() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            val uid = args.habitUid

            viewModel.fetchHabitByUid(uid)
            viewModel.uiState.collect { state ->
                when(state) {
                    is HabitDetailUiState.Empty -> {

                    }
                    is HabitDetailUiState.Failure -> {

                    }
                    is HabitDetailUiState.Loading -> {

                    }
                    is HabitDetailUiState.Success -> {
                        Log.d("Tag","Habit detail data is ${state.data}")
                        initUi(state.data)
                    }
                }
            }
        }
    }

    private fun initUi(habit: HabitDomainModel) {
        binding.cpbHabit.progress = habit.perDayGoalCount.toFloat()
        binding.cpbHabit.progressMax = habit.endGoalCount.toFloat()
        binding.tvPerDayCount.text = getString(R.string.per_day_count,habit.perDayGoalCount.toInt())
        binding.tvEndGoalCount.text = habit.endGoalCount.toString()

    }

}