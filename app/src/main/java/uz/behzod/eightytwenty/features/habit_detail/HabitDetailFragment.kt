package uz.behzod.eightytwenty.features.habit_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentHabitDetailBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class HabitDetailFragment : Fragment(R.layout.fragment_habit_detail) {

    private val binding by viewBinding(FragmentHabitDetailBinding::bind)
    private val viewModel: HabitDetailWithReduxViewModel by viewModels()
    private val args: HabitDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        viewModel.modifyUid(args.habitUid)
    }


    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { renderState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: HabitDetailState) {
        viewModel.fetchHabitByUid()

        state.habit?.let {
            binding.cpbHabit.progress = it.perDayGoalCount.toFloat()
            binding.cpbHabit.progressMax = it.endGoalCount.toFloat()
            binding.tvPerDayCount.text =
                getString(R.string.per_day_count, it.perDayGoalCount.toInt())
        }

    }

}