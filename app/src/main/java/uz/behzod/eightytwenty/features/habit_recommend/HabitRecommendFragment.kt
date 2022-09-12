package uz.behzod.eightytwenty.features.habit_recommend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentRecommendHabitBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class HabitRecommendFragment : Fragment(R.layout.fragment_recommend_habit) {

    private val binding by viewBinding(FragmentRecommendHabitBinding::bind)
    private lateinit var viewPagerAdapter: HabitRecommendViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        viewPagerAdapter = HabitRecommendViewPagerAdapter(this)
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String? = when (position) {
        OUR_CHOICE_INDEX -> getString(R.string.our_choice_text)
        HEALTH_INDEX -> getString(R.string.health_text)
        SPORT_INDEX -> getString(R.string.sport_text)
        STUDY_INDEX -> getString(R.string.study_text)
        THINKING_INDEX -> getString(R.string.thinking_text)
        else -> null
    }
}