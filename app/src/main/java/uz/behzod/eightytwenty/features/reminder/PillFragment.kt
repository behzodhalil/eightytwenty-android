package uz.behzod.eightytwenty.features.reminder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentPillBinding
import uz.behzod.eightytwenty.utils.extension.navigateTo
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class PillFragment : Fragment(R.layout.fragment_pill) {

    private val binding: FragmentPillBinding by viewBinding(FragmentPillBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.btnAddPill.setOnClickListener { route(PillRoute.AddPillRoute) }
    }

    private fun route(route: PillRoute) {
        when (route) {
            PillRoute.AddPillRoute -> {
                val direction = ReminderFragmentDirections.actionReminderFragmentToAddPillFragment()
                navigateTo(direction)
            }
        }
    }
}