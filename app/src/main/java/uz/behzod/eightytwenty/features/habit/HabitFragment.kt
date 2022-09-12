package uz.behzod.eightytwenty.features.habit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.tejpratapsingh.recyclercalendar.model.RecyclerCalendarConfiguration
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentHabitBinding
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.util.*

@AndroidEntryPoint
class HabitFragment : Fragment(R.layout.fragment_habit) {

    private val binding by viewBinding(FragmentHabitBinding::bind)
    private lateinit var horizontalAdapter: HorizontalCalendarAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerConfiguration()
        setupUI()
    }

    private fun setupUI() {
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

                }
            }
        )

        binding.rvCalendar.adapter = horizontalAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCalendar)
    }

    private fun onNavigateHabitRecommend() {
        binding.btnNewNote.setOnClickListener {
            val action = HabitFragmentDirections.actionHabitFragmentToHabitRecommendFragment()
            findNavController().navigate(action)
        }
    }

}