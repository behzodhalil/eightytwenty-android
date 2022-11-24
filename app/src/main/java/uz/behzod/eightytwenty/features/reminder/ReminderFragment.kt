package uz.behzod.eightytwenty.features.reminder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentReminderBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class ReminderFragment: Fragment(R.layout.fragment_reminder) {

    private val binding: FragmentReminderBinding by viewBinding(FragmentReminderBinding::bind)

    private lateinit var viewPager: ReminderViewPager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        viewPager = ReminderViewPager(this)
        binding.vpReminder.adapter = viewPager

        TabLayoutMediator(binding.tableLayout, binding.vpReminder) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String? = when(position) {
        BILL_INDEX -> getString(R.string.reminder_bill_text)
        PILL_INDEX -> getString(R.string.reminder_pill_text)
        WATER_INDEX -> getString(R.string.reminder_water_text)
        else -> null
    }


}
