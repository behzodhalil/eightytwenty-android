package uz.behzod.eightytwenty.features.productivity

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import kr.sns.ui_expandable_view.ExpandableItemAdapter
import kr.sns.ui_expandable_view.ExpandableItemRecyclerAdapter
import kr.sns.ui_expandable_view.ExpandableSelectionView
import kr.sns.ui_expandable_view.inflate
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

class ProductivityAdapter(
    private var list: List<NoteDomainModel>,
    var hint: String? = null,
    var counter: Int? = null
): ExpandableItemAdapter {

    @DrawableRes
    var collapsedStateResId: Int? = null
    @DrawableRes
    var expandedStateResId: Int? = null

    override fun inflateHeaderView(parent: ViewGroup): View {
        val view = parent.inflate(R.layout.header_item)
        val header = view.findViewById<TextView>(R.id.tv_header)
        val count = view.findViewById<TextView>(R.id.tv_count)
        header.text = hint
        count.text = counter.toString()
        return view
    }

    override fun inflateItemView(parent: ViewGroup): View {
        return parent.inflate(R.layout.view_holder_note)
    }

    override fun bindItemView(itemView: View, position: Int, selected: Boolean) {
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        title.text = list[position].title

        val desc = itemView.findViewById<TextView>(R.id.tv_desc)
        desc.text = list[position].description
    }

    override fun bindHeaderView(headerView: View, selectedIndices: List<Int>) {

    }

    override fun onViewStateChanged(headerView: View, state: ExpandableSelectionView.State) {
        val imageView = headerView.findViewById<ImageView>(R.id.iv_indicator)
        imageView.setImageResource(
            when (state) {
                ExpandableSelectionView.State.Expanded ->
                    expandedStateResId ?: R.drawable.ic_expand
                ExpandableSelectionView.State.Collapsed ->
                    collapsedStateResId ?: R.drawable.ic_collapse
            }
        )
    }

    override fun getItemsCount(): Int {
        return list.size
    }
}