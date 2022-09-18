package uz.behzod.eightytwenty.features.new_task_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.databinding.FragmentNewTaskDetailBinding
import uz.behzod.eightytwenty.utils.extension.toCalendar
import uz.behzod.eightytwenty.utils.extension.toZonedDateTime
import uz.behzod.eightytwenty.utils.formatter.DateTimeFormatter
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.ZonedDateTime

@AndroidEntryPoint
class NewTaskFragment : Fragment(R.layout.fragment_new_task_detail) {

    private val binding by viewBinding(FragmentNewTaskDetailBinding::bind)
    private var endDate: ZonedDateTime? = null
    private var reminder: ZonedDateTime? = null

    private var task: TaskEntity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDateOfCompletion()
        initReminder()
    }

    private fun setupUi() {

    }

    private fun initDateOfCompletion() {
        binding.btnSelectDateOfCompletion.setOnClickListener {
            MaterialDialog(requireContext()).show {
                lifecycleOwner(viewLifecycleOwner)
                datePicker(
                    requireFutureDate = true,
                    currentDate = ZonedDateTime.now().toCalendar()
                ) { _, timestamp ->
                    endDate = timestamp.toZonedDateTime()
                }
                positiveButton(R.string.text_btn_done) {
                    with(binding.btnSelectDateOfCompletion) {
                        text = endDate?.format(DateTimeFormatter.asMonthAndWeek(context))
                    }
                }
                /*dateTimePicker(
                    requireFutureDateTime = true,
                    currentDateTime = ZonedDateTime.now().toCalendar()
                ) { _, datetime ->
                    endDate = datetime.toZonedDateTime()
                    task?.endDate = datetime.toZonedDateTime()

                }
                positiveButton(R.string.text_btn_done) {
                    with(binding.btnSelectDateOfCompletion) {
                        text = endDate?.format(DateTimeFormatter.asTime(requireContext(), true))
                        debugger {
                            "End date is ${endDate?.format(DateTimeFormatter.asTime(context,true))}"
                        }
                    }
                }*/
            }
        }
    }

    private fun initReminder() {
        with(binding.btnSelectReminder) {
            setOnClickListener {
                MaterialDialog(requireContext()).show {
                    lifecycleOwner(viewLifecycleOwner)
                    dateTimePicker(
                        requireFutureDateTime = true,
                        currentDateTime = ZonedDateTime.now().toCalendar()
                    ) { _, timestamp ->
                        reminder = timestamp.toZonedDateTime()
                    }
                    positiveButton(R.string.text_btn_done) {
                        text = reminder?.format(DateTimeFormatter.asTime(requireContext(),true))
                    }
                }
            }
        }
    }
}