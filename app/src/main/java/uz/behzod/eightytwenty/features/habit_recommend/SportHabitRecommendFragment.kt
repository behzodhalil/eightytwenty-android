package uz.behzod.eightytwenty.features.habit_recommend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentSportHabitRecommendBinding
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.utils.extension.navigateTo
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class SportHabitRecommendFragment : Fragment(R.layout.fragment_sport_habit_recommend) {

    private val binding by viewBinding(FragmentSportHabitRecommendBinding::bind)
    private val viewModel: HabitRecommendViewModel by viewModels()

    private lateinit var adapter: HabitRecommendAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        adapter = HabitRecommendAdapter {
            val action =
                HabitRecommendFragmentDirections.actionHabitRecommendFragmentToNewHabitFragment(it.uid)
            navigateTo(action)
        }
        binding.rvRecommendHabit.adapter = adapter
        binding.rvRecommendHabit.setHasFixedSize(true)

        viewModel.modifyCategory("Sport")
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { renderState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: HabitRecommendState) {
        viewModel.fetchHabitRecommendsByCategory()

        if (state.habits.isNotEmpty()) {
            getHabitRecommends(state.habits)
        }
    }

    private fun getHabitRecommends(list: List<HabitRecommendDomainModel>) {
        return adapter.submitList(list)
    }
}