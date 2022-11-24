package uz.behzod.eightytwenty.features.new_habit

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.PerDayGoalType
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.databinding.FragmentNewHabitBinding
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.utils.constants.*
import uz.behzod.eightytwenty.utils.extension.Zero
import uz.behzod.eightytwenty.utils.extension.navigateTo
import uz.behzod.eightytwenty.utils.view.Colors
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.LocalDate

@AndroidEntryPoint
class NewHabitFragment : Fragment(R.layout.fragment_new_habit) {

    private val binding by viewBinding(FragmentNewHabitBinding::bind)
    private val args: NewHabitFragmentArgs by navArgs()
    private val viewModel: NewHabitViewModel by viewModels()

    private var daysOfWeekModel = Int.Zero
    private var frequencyTypeModel: Int = Int.Zero
    private var currentColorPosition = Colors.list[0]

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

        binding.btnSaveHabit.setOnClickListener {
            insertHabitWithSchedule()
        }

        initColorRecyclerView()
    }

    private fun initColorRecyclerView() {
        binding.rvColors.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvColors.adapter = HabitColorAdapter { view, position ->
            val button = view as? MaterialButton ?: return@HabitColorAdapter
            button.setBackgroundColor(button.context.getColor(Colors.list[position]))

            button.setOnClickListener {
                onDrawCurrentColor(position,button)
            }

        }
    }

    private fun onDrawCurrentColor(position: Int, view: MaterialButton) {
        (binding.rvColors.layoutManager as LinearLayoutManager).getChildAt(
            currentColorPosition
        ).let {
            (it as? MaterialButton)?.setStrokeWidthResource(R.dimen.eight)
            (it as? MaterialButton)?.setStrokeColorResource(R.color.solitude)
        }
        currentColorPosition = position
        view.setStrokeColorResource(R.color.color_primary_dark)
        view.setStrokeWidthResource(R.dimen.color_button_stroke_width)
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
            navigateTo(action)
        }
    }

    private fun insertHabitWithSchedule() {
        val title = binding.etHabitName.text.toString()
        val description = binding.etDescription.text.toString()
        val perDayCount = binding.etPerDayCount.text.toString()
        val endGoalCount: Long?=  binding.etEndGoalCount.text.toString().toLongOrNull()

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
        val timestamp = LocalDate.now().toString()

        binding.viewHolderSchedule.chipGroupDayOfWeek.forEach {
            if ((it as? Chip)?.isChecked == true) {
                daysOfWeekModel += when (it.id) {
                    R.id.chip_monday -> BIT_VALUE_OF_MONDAY
                    R.id.chip_tuesday -> BIT_VALUE_OF_TUESDAY
                    R.id.chip_wednesday -> BIT_VALUE_OF_WEDNESDAY
                    R.id.chip_thursday -> BIT_VALUE_OF_THURSDAY
                    R.id.chip_friday -> BIT_VALUE_OF_FRIDAY
                    R.id.chip_saturday -> BIT_VALUE_OF_SATURDAY
                    R.id.chip_sunday -> BIT_VALUE_OF_SUNDAY
                    else -> 0
                }
            }
        }

        binding.viewHolderSchedule.chipGroupFrequencyType.forEach {
            if ((it as? Chip)?.isChecked == true) {
                frequencyTypeModel += when (it.id) {
                    R.id.chip_daily -> BIT_VALUE_DAILY
                    R.id.chip_weekly -> BIT_VALUE_WEEKLY
                    R.id.chip_random -> BIT_VALUE_RANDOM
                    else -> 0
                }
            }
        }


        val habit = HabitDomainModel(
            title = title,
            description = description,
            perDayGoalCount = perDayCount.toLong(),
            perDayGoalType = perDayCountType,
            endGoalCount = endGoalCount ?: 0L,
            timestamp = timestamp,
            color = Colors.list[currentColorPosition],
            isComplete = false
        )

        val schedule = ScheduleEntity(
            frequencyTypes = frequencyTypeModel,
            daysOfWeek = daysOfWeekModel,
            dateOfCompletion = LocalDate.now().toString()
        )

        val schedules = arrayListOf<ScheduleEntity>()
        schedules.add(
            schedule
        )
        viewModel.insertHabit(
            habit,
            schedules
        ).run {
            Toast.makeText(requireContext(), "Successfully saved", Toast.LENGTH_SHORT).show()
        }
    }

}
