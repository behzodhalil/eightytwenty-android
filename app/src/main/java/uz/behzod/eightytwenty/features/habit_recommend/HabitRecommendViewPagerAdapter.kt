package uz.behzod.eightytwenty.features.habit_recommend

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val OUR_CHOICE_INDEX = 0
const val HEALTH_INDEX = 1
const val SPORT_INDEX = 2
const val STUDY_INDEX = 3
const val THINKING_INDEX = 4

class HabitRecommendViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    private val fragments: Map<Int, () -> Fragment> = mapOf(
        OUR_CHOICE_INDEX to {
            OurChoiceHabitRecommendFragment()
        },
        HEALTH_INDEX to {
            HealthHabitRecommendFragment()
        },
        SPORT_INDEX to {
            SportHabitRecommendFragment()
        },
        STUDY_INDEX to {
            StudyHabitRecommendFragment()
        },
        THINKING_INDEX to {
            ThinkingHabitRecommendFragment()
        }
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}