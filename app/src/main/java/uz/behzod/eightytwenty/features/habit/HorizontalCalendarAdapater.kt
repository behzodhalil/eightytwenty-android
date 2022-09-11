package uz.behzod.eightytwenty.features.habit

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tejpratapsingh.recyclercalendar.adapter.RecyclerCalendarBaseAdapter
import com.tejpratapsingh.recyclercalendar.model.RecyclerCalendarConfiguration
import com.tejpratapsingh.recyclercalendar.model.RecyclerCalenderViewItem
import com.tejpratapsingh.recyclercalendar.utilities.CalendarUtils
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.ItemWeeklyCalendarBinding
import java.util.*

class HorizontalCalendarAdapter(
    startDate: Date,
    endDate: Date,
    private val configuration: RecyclerCalendarConfiguration,
    private var selectedDate: Date,
    private val dateSelectListener: OnDateSelected
) : RecyclerCalendarBaseAdapter(startDate, endDate, configuration) {

    interface OnDateSelected {
        fun onDateSelected(date: Date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemWeeklyCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MonthCalendarViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        calendarItem: RecyclerCalenderViewItem
    ) {
        val monthViewHolder: MonthCalendarViewHolder = holder as MonthCalendarViewHolder
        val context: Context = monthViewHolder.itemView.context
        monthViewHolder.itemView.visibility = View.VISIBLE

        monthViewHolder.itemView.setOnClickListener(null)

        monthViewHolder.itemView.background = null

        monthViewHolder.textViewDay.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.black_40
            )
        )
        monthViewHolder.textViewDate.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.black
            )
        )

        if (calendarItem.isHeader) {
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar.time = calendarItem.date

            val month: String = CalendarUtils.dateStringFromFormat(
                locale = configuration.calendarLocale,
                date = selectedCalendar.time,
                format = CalendarUtils.DISPLAY_MONTH_FORMAT
            ) ?: ""
            val year = selectedCalendar[Calendar.YEAR].toLong()

            monthViewHolder.textViewDay.text = year.toString()
            monthViewHolder.textViewDate.text = month

            monthViewHolder.itemView.setOnClickListener(null)
        } else if (calendarItem.isEmpty) {
            monthViewHolder.itemView.visibility = View.GONE
            monthViewHolder.textViewDay.text = ""
            monthViewHolder.textViewDate.text = ""
        } else {
            val calendarDate = Calendar.getInstance()
            calendarDate.time = calendarItem.date

            val stringCalendarTimeFormat: String =
                CalendarUtils.dateStringFromFormat(
                    locale = configuration.calendarLocale,
                    date = calendarItem.date,
                    format = CalendarUtils.DB_DATE_FORMAT
                )
                    ?: ""
            val stringSelectedTimeFormat: String =
                CalendarUtils.dateStringFromFormat(
                    locale = configuration.calendarLocale,
                    date = selectedDate,
                    format = CalendarUtils.DB_DATE_FORMAT
                ) ?: ""

            if (stringCalendarTimeFormat == stringSelectedTimeFormat) {

                monthViewHolder.textViewDate.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
                monthViewHolder.textViewDay.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.black_40
                    )
                )
                monthViewHolder.textViewDate.background =
                    ContextCompat.getDrawable(
                        context, R.drawable.bg_selected_filled
                    )
            }

            val day: String = CalendarUtils.dateStringFromFormat(
                locale = configuration.calendarLocale,
                date = calendarDate.time,
                format = CalendarUtils.DISPLAY_WEEK_DAY_FORMAT
            ) ?: ""

            monthViewHolder.textViewDay.text = day

            monthViewHolder.textViewDate.text =
                CalendarUtils.dateStringFromFormat(
                    locale = configuration.calendarLocale,
                    date = calendarDate.time,
                    format = CalendarUtils.DISPLAY_DATE_FORMAT
                ) ?: ""

            monthViewHolder.itemView.setOnClickListener {
                selectedDate = calendarItem.date
                dateSelectListener.onDateSelected(calendarItem.date)
                notifyDataSetChanged()
            }
        }
    }


    class MonthCalendarViewHolder(binding: ItemWeeklyCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val textViewDay = binding.tvDay
        val textViewDate = binding.tvDate
    }
}