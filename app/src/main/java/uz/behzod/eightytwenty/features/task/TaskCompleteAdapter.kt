package uz.behzod.eightytwenty.features.task

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import kr.sns.ui_expandable_view.ExpandableItemAdapter
import kr.sns.ui_expandable_view.ExpandableSelectionView
import kr.sns.ui_expandable_view.inflate
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.model.TaskDomainModel

class TaskCompleteAdapter(
    var title: String,
    var counter: String
): ExpandableItemAdapter {

    @DrawableRes
    var collapsedStateResId: Int? = null
    @DrawableRes
    var expandedStateResId: Int? = null

    private val list = mutableListOf<TaskEntity>()

    override fun inflateHeaderView(parent: ViewGroup): View {
        val view = parent.inflate(R.layout.header_item)
        val header = view.findViewById<TextView>(R.id.tv_header)
        val count = view.findViewById<TextView>(R.id.tv_count)
        header.text = title
        count.text = counter
        return view
    }

    override fun inflateItemView(parent: ViewGroup): View {
        return parent.inflate(R.layout.view_holder_task)
    }

    override fun bindItemView(itemView: View, position: Int, selected: Boolean) {
        val title = itemView.findViewById<TextView>(R.id.tv_task_title)
        title.text = list[position].title

        val desc = itemView.findViewById<TextView>(R.id.tv_task_timestamp)
        desc.text = list[position].timestamp.toString()
    }

    override fun bindHeaderView(headerView: View, selectedIndices: List<Int>) {}

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

    fun setData(values: List<TaskEntity>) {
        list.addAll(values)
    }
}