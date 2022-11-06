package uz.behzod.eightytwenty.features.select_productivity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentSelectProductivityBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class SelectProductivityFragment : Fragment(R.layout.fragment_select_productivity) {

    private val binding: FragmentSelectProductivityBinding by viewBinding(
        FragmentSelectProductivityBinding::bind)
    private lateinit var viewPagerAdapter: SelectProductivityViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        setupViewPager()
        setupTabLayout()
        binding.spIvCancel.setOnClickListener { route(SelectProductivityRoute.BackRoute) }
    }

    private fun setupViewPager() {
        viewPagerAdapter = SelectProductivityViewPagerAdapter.instance(this)
        binding.spViewPager.adapter = viewPagerAdapter
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.spTabLayout, binding.spViewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            SelectProductivityViewPagerAdapter.TASK_INDEX -> "Задачи"
            SelectProductivityViewPagerAdapter.NOTE_INDEX -> "Заметки"
            SelectProductivityViewPagerAdapter.HABIT_INDEX -> "Привычки"
            else -> null
        }
    }

    private fun route(route: SelectProductivityRoute) {
        when (route) {
            SelectProductivityRoute.BackRoute -> {
                findNavController().popBackStack()
            }
        }
    }
}