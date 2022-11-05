package kr.sns.ui_expandable_view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class ExpandableRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): RecyclerView(context,attrs,defStyleAttr) {

    private var isAnimated = false
    var maxHeight = Int.MIN_VALUE

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        val newHeightSpec = when {
            isAnimated -> heightSpec
            else -> MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)
        }
        super.onMeasure(widthSpec, newHeightSpec)
    }

    fun expand(duration: Long) {
        this.isAnimated = true
        this.expand(maxHeight,duration) {
            this.isAnimated = false
        }
    }
    fun collapse(duration: Long) {
        this.isAnimated = false
        this.collapse(duration) {
            this.isAnimated = true
        }
    }

}