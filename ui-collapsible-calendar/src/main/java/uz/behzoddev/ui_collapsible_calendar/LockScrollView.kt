package uz.behzoddev.ui_collapsible_calendar

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.shrikanthravi.collapsiblecalendarview.view.OnSwipeTouchListener

/**
 * Created by shrikanthravi on 07/03/18.
 */

class LockScrollView : ScrollView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
    var swipeTouchListener: OnSwipeTouchListener? = null
    fun setParams(swipeTouchListener: OnSwipeTouchListener){
        this.swipeTouchListener = swipeTouchListener
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        swipeTouchListener?.onTouch(this, ev).let {
            if(it==null){
                return false;
            }
            else {
                return it
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        super.onTouchEvent(ev)
        return true
    }
}