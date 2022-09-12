package uz.behzod.eightytwenty.features.new_habit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.Frequency
import uz.behzod.eightytwenty.data.local.entities.PerDayGoalType
import uz.behzod.eightytwenty.databinding.FragmentNewHabitBinding
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.LocalDate

@AndroidEntryPoint
class NewHabitFragment : Fragment(R.layout.fragment_new_habit) {

    private val binding by viewBinding(FragmentNewHabitBinding::bind)
    private val args: NewHabitFragmentArgs by navArgs()
    private val viewModel: NewHabitViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

        fetchHabitRecommendUi()

        onNavigateToHabitRecommend()

    }

    private fun setupUI() {
        val perDayGoalTypeAdapter = ArrayAdapter(
            requireContext(),
            R.layout.view_holder_auto_complete,
            perDayGoalType
        )

        binding.actGoalType.setAdapter(perDayGoalTypeAdapter)

        binding.selectFrequencyTypeToggleGroup.setOnSelectListener {
            Toast.makeText(requireContext(), it.text, Toast.LENGTH_SHORT).show()
        }

        binding.btnSaveHabit.setOnClickListener {
            insertHabit()
        }

    }

    private fun fetchHabitRecommendUi() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (args.recommendUid != 0L) {
                    viewModel.fetchHabitRecommendByUid(args.recommendUid)
                }

                viewModel.uiState.collect { state ->
                    when (state) {
                        is NewHabitUiState.Empty -> {

                        }
                        is NewHabitUiState.Failure -> {

                        }
                        is NewHabitUiState.Loading -> {

                        }
                        is NewHabitUiState.Success -> {
                            initUi(state.data)
                        }
                    }
                }
            }
        }
    }

    private fun initUi(habitRecommend: HabitRecommendDomainModel) {
        binding.apply {
            etHabitName.setText(habitRecommend.title)
            etDescription.setText(habitRecommend.description)
            etPerDayCount.setText(habitRecommend.perDayGoalCount.toString())
            when (habitRecommend.perDayGoalType) {
                PerDayGoalType.ONCE -> actGoalType.setText("Раз")
                PerDayGoalType.HOUR -> actGoalType.setText("Час")
                PerDayGoalType.MINUTES -> actGoalType.setText("Минута")
                PerDayGoalType.PAGES -> actGoalType.setText("Стакан")
                PerDayGoalType.CUP -> actGoalType.setText("Страница")
            }
        }
    }

    private fun onNavigateToHabitRecommend() {
        binding.ivBack.setOnClickListener {
            val action = NewHabitFragmentDirections.actionNewHabitFragmentToHabitRecommendFragment()
            findNavController().navigate(action)
        }
    }

    private fun insertHabit() {
        val title = binding.etHabitName.text.toString()
        val description = binding.etDescription.text.toString()
        val perDayCount = binding.etPerDayCount.text.toString()
        val endGoalCount = binding.etEndGoalCount.text.toString().toLong()
        var perDayCountType = PerDayGoalType.ONCE

        when (binding.actGoalType.text.toString()) {
            "Час" -> {
                perDayCountType = PerDayGoalType.HOUR
            }
            "Раз" -> {
                perDayCountType = PerDayGoalType.ONCE
            }
            "Стакан" -> {
                perDayCountType = PerDayGoalType.CUP
            }
            "Страница" -> {
                perDayCountType = PerDayGoalType.PAGES
            }
            "Минута" -> {
                perDayCountType = PerDayGoalType.MINUTES
            }
        }

        var selectDay = Frequency.DAILY

        binding.selectFrequencyTypeToggleGroup.setOnSelectListener {
            when (it.text) {
                "По дням" -> {
                    selectDay = Frequency.DAILY
                }
                "По неделям " -> {
                    selectDay = Frequency.WEEK
                }
                "Произвольно" -> {
                    selectDay = Frequency.RANDOM
                }
            }
        }

        val timestamp = LocalDate.now().toString()
        val color = "Blue"
        val isComplete = false

        viewModel.insertHabit(
            HabitDomainModel(
                title = title,
                description = description,
                perDayGoalCount = perDayCount.toLong(),
                endGoalCount =endGoalCount,
                perDayGoalType = perDayCountType,
                frequency = selectDay,
                timestamp = timestamp,
                color = color,
                isComplete = isComplete
            )
        ).run {
            Log.d("Tag","Habit is successfully saved")
        }

    }
}