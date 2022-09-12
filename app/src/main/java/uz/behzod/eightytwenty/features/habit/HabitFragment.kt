package uz.behzod.eightytwenty.features.habit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.text.SimpleDateFormat
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
        fetchHabitsByDate()
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

        horizontalAdapter = HorizontalCalendarAdapter(
            startDate = startCalendar.time,
            endDate = endCalendar.time,
            configuration = configuration,
            selectedDate = date,

            dateSelectListener = object : HorizontalCalendarAdapter.OnDateSelected {
                override fun onDateSelected(date: Date) {
                    val format = SimpleDateFormat("YYYY-MM-DD")
                    val timestamp = CalendarUtils.dateStringFromFormat(
                        locale = configuration.calendarLocale,
                        date = date,
                        format = "yyyy-MM-dd"
                    )
                    Log.d("Tag","Current date is $timestamp")
                    viewModel.fetchHabitsByDate(timestamp!!)
                }
            }
        )

        binding.rvCalendar.adapter = horizontalAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCalendar)
    }

    private fun initRecyclerView() {
        adapter = HabitAdapter()
        binding.rvHabits.adapter = adapter
    }

    private fun fetchHabitsByDate() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when(state) {
                        is HabitUIState.Empty -> {

                        }
                        is HabitUIState.Failure -> {

                        }
                        is HabitUIState.Loading -> {

                        }
                        is HabitUIState.Success -> {
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            adapter.submitList(state.data)
                            Log.d("Tag","Result data is ${state.data}")
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