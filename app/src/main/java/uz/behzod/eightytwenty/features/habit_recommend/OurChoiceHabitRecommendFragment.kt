package uz.behzod.eightytwenty.features.habit_recommend

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentOurChoiceHabitRecommendBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class OurChoiceHabitRecommendFragment : Fragment(R.layout.fragment_our_choice_habit_recommend) {

    private val binding by viewBinding(FragmentOurChoiceHabitRecommendBinding::bind)
    private lateinit var adapter: HabitRecommendAdapter
    private val viewModel: HabitRecommendViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        initRecyclerView()

        fetchHabitRecommendsByCategory()
    }

    private fun initRecyclerView() {
        adapter = HabitRecommendAdapter {
            val action =
            HabitRecommendFragmentDirections.actionHabitRecommendFragmentToNewHabitFragment(it.uid)
            findNavController().navigate(action)
        }
        binding.rvRecommendHabit.adapter = adapter
        binding.rvRecommendHabit.setHasFixedSize(true)
    }

    private fun fetchHabitRecommendsByCategory() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.fetchHabitRecommendByCategory("Our Choice")
            viewModel.uiState.collect { result ->
                when(result) {
                    is HabitRecommendUIState.Empty -> {

                    }
                    is HabitRecommendUIState.Failure -> {

                    }
                    is HabitRecommendUIState.Loading -> {

                    }
                    is HabitRecommendUIState.Success -> {
                        adapter.submitList(result.data)
                        Log.d("Tag","Result data is $result")
                    }
                }
            }
        }
    }

}