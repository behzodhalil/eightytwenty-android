package uz.behzod.eightytwenty.features.new_task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.databinding.FragmentNewTaskDetailBinding
import uz.behzod.eightytwenty.utils.constants.*
import uz.behzod.eightytwenty.utils.extension.*
import uz.behzod.eightytwenty.utils.formatter.DateTimeFormatter
import uz.behzod.eightytwenty.utils.formatter.StringFormatter
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.ZonedDateTime


@AndroidEntryPoint
class NewTaskFragment : Fragment(R.layout.fragment_new_task_detail) {

    private val binding by viewBinding(FragmentNewTaskDetailBinding::bind)
    private val viewModel: NewTaskViewModel by viewModels()

    private var endDate: ZonedDateTime? = null
    private var reminder: ZonedDateTime? = null
    private var daysOfWeekModel: Int = Int.Zero
    private var frequencyTypeModel: Int = Int.Zero

    private var isShown = true

    private var task: TaskEntity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDateOfCompletion()
        initReminder()
        initRepeat()
        initSchedule()

        onNavigateCatalog()
        onNavigateToNewNote()
        displayNewNote()
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
                        text = reminder?.format(DateTimeFormatter.asTime(requireContext(), true))
                    }
                }
            }
        }
    }

    private fun initRepeat() {
        with(binding) {
            btnSelectRepeat.setOnClickListener {
                isShown = if (isShown) {
                    viewHolderSchedule.root.show()
                    btnApplySchedule.show()
                    btnCancelSchedule.show()
                    initSchedule()
                    false
                } else {
                    viewHolderSchedule.root.gone()
                    btnApplySchedule.gone()
                    btnCancelSchedule.gone()
                    true
                }

            }
        }
    }

    private fun initSchedule() = with(binding.viewHolderSchedule) {

        chipMondayTask.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                daysOfWeekModel += BIT_VALUE_OF_MONDAY
            } else {
                daysOfWeekModel -= BIT_VALUE_OF_MONDAY
            }
        }

        chipTuesdayTask.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                daysOfWeekModel += BIT_VALUE_OF_TUESDAY
            } else {
                daysOfWeekModel -= BIT_VALUE_OF_TUESDAY
            }
        }

        chipWednesdayTask.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                daysOfWeekModel += BIT_VALUE_OF_WEDNESDAY
            } else {
                daysOfWeekModel -= BIT_VALUE_OF_WEDNESDAY
            }
        }

        chipThursdayTask.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                daysOfWeekModel += BIT_VALUE_OF_THURSDAY
            } else {
                daysOfWeekModel -= BIT_VALUE_OF_THURSDAY
            }
        }

        chipFridayTask.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                daysOfWeekModel += BIT_VALUE_OF_FRIDAY
            } else {
                daysOfWeekModel -= BIT_VALUE_OF_FRIDAY
            }
        }

        chipSaturdayTask.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                daysOfWeekModel += BIT_VALUE_OF_SATURDAY
            } else {
                daysOfWeekModel -= BIT_VALUE_OF_SATURDAY
            }
        }

        chipSundayTask.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                daysOfWeekModel += BIT_VALUE_OF_SUNDAY
            } else {
                daysOfWeekModel -= BIT_VALUE_OF_SUNDAY
            }
        }

        binding.btnApplySchedule.setOnClickListener {
            val days = formatDayOfWeek()

            if (days.isEmpty()) {
                binding.btnSelectRepeat.text = getString(R.string.text_don_t_repeat)
            } else {
                binding.btnSelectRepeat.text = days
            }

            binding.viewHolderSchedule.root.gone()
            binding.btnApplySchedule.gone()
            binding.btnCancelSchedule.gone()
            isShown = true
        }

    }

    private fun onNavigateCatalog() {
        with(binding.btnSelectFolder) {
            setOnClickListener {
                val dialog = CatalogDialog()
                transaction(dialog)
                dialog.setFetchingNameAndUid(object : CatalogDialog.CatalogDialogListener {
                    override fun fetchNameAndUid() {
                        onUpdateCatalogNameAndUid()
                    }
                })
            }
        }
    }

    private fun formatDayOfWeek(): String {
        val lists = StringFormatter.parseDayOfWeek(daysOfWeekModel)
        return StringFormatter.asDayOfWeek(requireContext(), true, lists)
    }

    private fun onUpdateCatalogNameAndUid() {
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "TaskCatalogName",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getString("TaskCatalogName")
            binding.btnSelectFolder.text = result.toString()
        }
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "TaskCatalogUid",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getString("TaskCatalogUid")

        }
    }

    private fun onNavigateToNewNote() {
        binding.cvNote.setOnClickListener {
            val route = NewTaskFragmentDirections.actionNewTaskFragmentToNewNoteFragment()
            navController.navigate(route)
        }
    }

    private fun displayNewNote() {
        binding.cvNote.setOnClickListener {
            val screen = NewNoteDialog()
            transaction(screen)
            screen.setCloseListener(object : NewNoteDialog.NewNoteListener {
                override fun close() {
                    listenerNewNote()
                }
            })
        }

    }

    private fun listenerNewNote() {
        supportFragmentManager.setFragmentResultListener(
            "NoteTitle",
            viewLifecycleOwner
        ) { _, bundle ->
            binding.viewHolderNote.tvTitle.text = bundle.getString("NoteTitle")
        }
        supportFragmentManager.setFragmentResultListener(
            "NoteDescription",
            viewLifecycleOwner
        ) { _, bundle ->
            binding.viewHolderNote.tvDesc.text = bundle.getString("NoteDescription")
        }
        supportFragmentManager.setFragmentResultListener(
            "NoteTimestamp",
            viewLifecycleOwner
        ) { _, bundle ->
            binding.viewHolderNote.tvDate.text = bundle.getString("NoteTimestamp")
        }
        binding.cvNote.gone()
        binding.viewHolderNote.root.show()
    }
}