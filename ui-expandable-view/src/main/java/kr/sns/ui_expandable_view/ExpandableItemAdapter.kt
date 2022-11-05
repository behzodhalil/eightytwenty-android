package kr.sns.ui_expandable_view

import android.view.View
import android.view.ViewGroup

interface ExpandableItemAdapter {
    fun inflateHeaderView(parent: ViewGroup): View

    fun inflateItemView(parent: ViewGroup): View

    fun bindItemView(itemView: View, position: Int, selected: Boolean)

    fun bindHeaderView(headerView: View, selectedIndices: List<Int>)

    fun onViewStateChanged(headerView: View, state: ExpandableSelectionView.State)

    fun getItemsCount(): Int
}