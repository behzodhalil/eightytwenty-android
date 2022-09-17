package uz.behzod.eightytwenty.features.habit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.tejpratapsingh.recyclercalendar.model.RecyclerCalendarConfiguration
import com.tejpratapsingh.recyclercalendar.utilities.CalendarUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentHabitBinding
import uz.behzod.eightytwenty.utils.extension.hide
import uz.behzod.eightytwenty.utils.extension.show
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.util.*

@AndroidEntryPoint
class HabitFragment : Fragment(R.layout.fragment_habit) {

    private val binding by viewBinding(FragmentHabitBinding::bind)
    private lateinit var horizontalAdapter: HorizontalCalendarAdapter
    private lateinit var adapter: HabitAdapter

    private val viewModel: HabitViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerConfiguration()
        setupUI()
    }

    private fun setupUI() {
        initRecyclerView()
        onNavigateHabitRecommend()
    }

    private fun setupRecyclerConfiguration() {

        val date = Date()
        date.time = System.currentTimeMillis()

        val startCalendar = Calendar.getInstance()
        val endCalendar = Calendar.getInstance()

        endCalendar.time = date
        endCalendar.add(Calendar.MONTH, 1)


        val configuration =
            RecyclerCalendarConfiguration(
                calenderViewType = RecyclerCalendarConfiguration.CalenderViewType.HORIZONTAL,
                calendarLocale = Locale.getDefault(),
                includeMonthHeader = true
            )

        configuration.weekStartOffset = RecyclerCalendarConfiguration.START_DAY_OF_WEEK.SUNDAY

        val timestamp = CalendarUtils.dateStringFromFormat(
            locale = configuration.calendarLocale,
            date = date,
            format = "yyyy-MM-dd"
        ) ?: ""
        searchByDate(timestamp)
        horizontalAdapter = HorizontalCalendarAdapter(
            startDate = startCalendar.time,
            endDate = endCalendar.time,
            configuration = configuration,
            selectedDate = date,

            dateSelectListener = object : HorizontalCalendarAdapter.OnDateSelected {
                override fun onDateSelected(date: Date) {
                    val currentTimestamp = CalendarUtils.dateStringFromFormat(
                        locale = configuration.calendarLocale,
                        date = date,
                        format = "yyyy-MM-dd"
                    ) ?: ""
                    Log.d("Tag", "Current date is $currentTimestamp")
                    searchByDate(currentTimestamp)

                }
            }
        )

        binding.rvCalendar.adapter = horizontalAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCalendar)
    }

    private fun initRecyclerView() {
        adapter = HabitAdapter {
            val action = HabitFragmentDirections.actionHabitFragmentToHabitDetailFragment(it.uid)
            findNavController().navigate(action)
        }
        binding.rvHabits.adapter = adapter
        binding.rvHabits.setHasFixedSize(true)
    }

    private fun searchByDate(date: String) {
        fetchHabitsByDate(timestamp =date )
    }
    private fun fetchHabitsByDate(timestamp: String) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchHabitsByDate(timestamp)
                viewModel.uiState.collect { state ->
                    when (state) {
                        is HabitUIState.Empty -> {
                            binding.rvHabits.hide()
                            Log.d("Tag", "State is empty")
                        }
                        is HabitUIState.Failure -> {
                            Log.d("Tag", "State is failure")
                        }
                        is HabitUIState.Loading -> {
                            Log.d("Tag", "State is loading")
                        }
                        is HabitUIState.Success -> {
                            binding.rvHabits.show()
                            adapter.submitList(state.data)
                            Log.d("Tag", "Result data is ${state.data}")
                        }
                    }
                }
            }
            }

    }

    private fun onNavigateHabitRecommend() {
        binding.btnNewNote.setOnClickListener {
            val action = HabitFragmentDirections.actionHabitFragmentToHabitRecommendFragment()
            findNavController().navigate(action)
        }
    }

}