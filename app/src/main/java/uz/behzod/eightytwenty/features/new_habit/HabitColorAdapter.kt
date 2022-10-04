package uz.behzod.eightytwenty.features.new_habit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.ViewHolderColorBinding
import uz.behzod.eightytwenty.utils.view.Colors

class HabitColorAdapter(
    private val onBind: (view: View, position: Int) -> Unit
) : RecyclerView.Adapter<HabitColorAdapter.ViewHolder>() {
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_color, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        onBind(holder.view, position)

    override fun getItemCount() = Colors.list.size
}