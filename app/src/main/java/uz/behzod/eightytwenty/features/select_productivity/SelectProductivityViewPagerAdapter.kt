package uz.behzod.eightytwenty.features.select_productivity

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private typealias Fragments = Map<Int, () -> Fragment>


class SelectProductivityViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    companion object {
        const val TASK_INDEX = 0
        const val NOTE_INDEX = 1
        const val HABIT_INDEX = 2

        fun instance(fragment: Fragment): SelectProductivityViewPagerAdapter {
            return SelectProductivityViewPagerAdapter(fragment)
        }
    }

    private val fragments: Fragments = mapOf(
        TASK_INDEX to { SelectTaskProductivityFragment() },
        NOTE_INDEX to { SelectNoteProductivityFragment() },
        HABIT_INDEX to { SelectHabitProductivityFragment() }
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}