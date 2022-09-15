package uz.behzod.eightytwenty.features.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.utils.extension.layoutInflaterService
import android.widget.TextView as TextView1

class OnboardingViewPagerAdapter(
    private val context: Context,
    screens: List<OnboardingItem>
) : PagerAdapter() {

    private var listScreen: List<OnboardingItem> = screens

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflater = context.layoutInflaterService as LayoutInflater
        val screen = inflater.inflate(R.layout.view_holder_onboarding, null)

        val image = screen.findViewById<ImageView>(R.id.iv_screen)
        val title = screen.findViewById<TextView1>(R.id.tv_title)
        val description = screen.findViewById<TextView1>(R.id.tv_description)

        title.text = listScreen[position].title
        description.text = listScreen[position].description
        image.setImageResource(listScreen[position].image)

        container.addView(screen)

        return screen
    }

    override fun getCount(): Int {
        return listScreen.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}