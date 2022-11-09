package uz.behzod.eightytwenty.features.reminder

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val BILL_INDEX = 0
const val PILL_INDEX = 1
const val WATER_INDEX = 2

class ReminderViewPager(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {

    private val fragments: Map<Int, () -> Fragment> = mapOf(
        BILL_INDEX to { BillFragment() },
        PILL_INDEX to { PillFragment() },
        WATER_INDEX to { WaterFragment() }
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}