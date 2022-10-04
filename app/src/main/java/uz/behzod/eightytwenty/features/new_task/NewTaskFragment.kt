package uz.behzod.eightytwenty.features.new_task

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentNewTaskDetailBinding
import uz.behzod.eightytwenty.utils.constants.*
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_ACTION_IMPORT_SUCCESS
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_INTENT_DATA
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_INTENT_EXTRA
import uz.behzod.eightytwenty.utils.constants.BroadcastConstants.BROADCAST_INTENT_STATUS
import uz.behzod.eightytwenty.utils.constants.ServiceConstants.SERVICE_ACTION_START
import uz.behzod.eightytwenty.utils.constants.ServiceConstants.SERVICE_ACTION_WITH_BROADCAST
import uz.behzod.eightytwenty.utils.constants.ServiceConstants.SERVICE_EXTRA_UID
import uz.behzod.eightytwenty.utils.extension.*
import uz.behzod.eightytwenty.utils.formatter.DateTimeFormatter
import uz.behzod.eightytwenty.utils.formatter.StringFormatter
import uz.behzod.eightytwenty.utils.services.FileImportService
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.ZonedDateTime
import java.util.*


@AndroidEntryPoint
class NewTaskFragment : Fragment(R.layout.fragment_new_task_detail) {

    private val binding by viewBinding(FragmentNewTaskDetailBinding::bind)
    private val viewModel: NewTaskWithReduxViewModel by viewModels()

    private var isShown = true

    private lateinit var attachmentLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        attachmentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val attachmentId = UUID.randomUUID().toString()
                    context?.startService(Intent(context, FileImportService::class.java).apply {
                        action = SERVICE_ACTION_START
                        data = it.data?.data
                        putExtra(SERVICE_EXTRA_UID, attachmentId)
                    })
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupFragmentResultListeners()

        observeState()
    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(receiver, IntentFilter(SERVICE_ACTION_WITH_BROADCAST))
    }



    private fun formatDayOfWeek(): String {
        val lists = StringFormatter.parseDayOfWeek(viewModel.currentState.scheduleDaysOfWeek)
        return StringFormatter.asDayOfWeek(requireContext(), true, lists)
    }

    private fun setupFragmentResultListeners() {
        supportFragmentManager.setFragmentResultListener(
            "NoteTitle",
            viewLifecycleOwner
        ) { _, bundle ->
            viewModel.modifyNoteTitle(bundle.getString("NoteTitle") ?: "")
        }
        supportFragmentManager.setFragmentResultListener(
            "NoteDescription",
            viewLifecycleOwner
        ) { _, bundle ->
            viewModel.modifyNoteDesc(bundle.getString("NoteDescription") ?: "")
        }
        supportFragmentManager.setFragmentResultListener(
            "NoteTimestamp",
            viewLifecycleOwner
        ) { _, bundle ->
            viewModel.modifyNoteTimestamp(bundle.getString("NoteTimestamp") ?: "")
        }

        supportFragmentManager.setFragmentResultListener(
            "TaskCatalogName",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getString("TaskCatalogName") ?: "Входящий"
            viewModel.modifyTaskGroupName(result)
        }
        supportFragmentManager.setFragmentResultListener(
            "TaskCatalogUid",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getLong("TaskCatalogUid")
            viewModel.modifyTaskGroupUid(result)
        }


    }

    private fun openDocument() {
        attachmentLauncher.launch(
            Intent(Intent.ACTION_OPEN_DOCUMENT).setType(INTENT_OPEN_DOCUMENT_TYPE)
        )
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == SERVICE_ACTION_WITH_BROADCAST) {
                when (intent.getStringExtra(BROADCAST_INTENT_STATUS)) {
                    BROADCAST_ACTION_IMPORT_SUCCESS -> {
                        if (intent.hasExtra(BROADCAST_INTENT_DATA)
                            && intent.hasExtra(BROADCAST_INTENT_EXTRA)
                        ) {

                            val id = intent.getStringExtra(BROADCAST_INTENT_DATA)
                            val attachmentName =
                                intent.getStringExtra(BROADCAST_INTENT_EXTRA) ?: ""

                            viewModel.modifyAttachmentState(attachmentName, isLoaded = true)

                        }
                    }
                }
            }
        }
    }

    private fun setupView() {
        binding.tvTimestamp.text = ZonedDateTime.now().toString()

        binding.etNewTaskTitle.addTextChangedListener {
            viewModel.modifyTaskTitle(it.asStringOrEmpty())
        }
        binding.cvAttachment.setOnClickListener {
            if (viewModel.currentState.isReadStorePermission) {
                openDocument()
            }
        }

        binding.btnSelectReminder.setOnClickListener {
            MaterialDialog(requireContext()).show {
                lifecycleOwner(viewLifecycleOwner)
                dateTimePicker(
                    requireFutureDateTime = true,
                    currentDateTime = ZonedDateTime.now().toCalendar()
                ) { _, timestamp ->
                    timestamp.toZonedDateTime()?.let {
                        viewModel.modifyTaskReminder(it)
                    }
                }
                positiveButton(R.string.text_btn_done) {
                    viewModel.hasDisplayedReminder(true)
                }
            }
        }

        binding.cvNote.setOnClickListener {
            val screen = NewNoteDialog()
            transaction(screen)
            screen.setCloseListener(object : NewNoteDialog.NewNoteListener {
                override fun close() {
                    viewModel.hasDisplayedSingleNote(true)
                }
            })
        }

        binding.viewHolderSchedule.chipMondayTask.setOnCheckedChangeListener { _, isChecked ->
            viewModel.modifyScheduleDaysOfWeek(value = BIT_VALUE_OF_MONDAY, isChecked = isChecked)
        }

        binding.viewHolderSchedule.chipTuesdayTask.setOnCheckedChangeListener { _, isChecked ->
            viewModel.modifyScheduleDaysOfWeek(BIT_VALUE_OF_TUESDAY, isChecked)
        }

        binding.viewHolderSchedule.chipWednesdayTask.setOnCheckedChangeListener { _, isChecked ->
            viewModel.modifyScheduleDaysOfWeek(BIT_VALUE_OF_WEDNESDAY, isChecked)
        }

        binding.viewHolderSchedule.chipThursdayTask.setOnCheckedChangeListener { _, isChecked ->
            viewModel.modifyScheduleDaysOfWeek(BIT_VALUE_OF_THURSDAY, isChecked)
        }

        binding.viewHolderSchedule.chipFridayTask.setOnCheckedChangeListener { _, isChecked ->
            viewModel.modifyScheduleDaysOfWeek(BIT_VALUE_OF_FRIDAY, isChecked)
        }

        binding.viewHolderSchedule.chipSaturdayTask.setOnCheckedChangeListener { _, isChecked ->
            viewModel.modifyScheduleDaysOfWeek(BIT_VALUE_OF_SATURDAY, isChecked)
        }

        binding.viewHolderSchedule.chipSundayTask.setOnCheckedChangeListener { _, isChecked ->
            viewModel.modifyScheduleDaysOfWeek(BIT_VALUE_OF_SUNDAY, isChecked)
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
            viewModel.hasDisplayedReminder(true)
        }

        binding.btnSelectFolder.setOnClickListener {
            val dialog = CatalogDialog()
            transaction(dialog)
            dialog.setFetchingNameAndUid(object : CatalogDialog.CatalogDialogListener {
                override fun fetchNameAndUid() {
                    viewModel.hasDisplayedGroup(true)
                }
            })
        }

        binding.btnSelectRepeat.setOnClickListener {
            isShown = if (isShown) {
                binding.viewHolderSchedule.root.show()
                binding.btnApplySchedule.show()
                binding.btnCancelSchedule.show()
                false
            } else {
                binding.viewHolderSchedule.root.gone()
                binding.btnApplySchedule.gone()
                binding.btnCancelSchedule.gone()
                true
            }
        }

        binding.btnSelectDateOfCompletion.setOnClickListener {
            MaterialDialog(requireContext()).show {
                lifecycleOwner(viewLifecycleOwner)
                datePicker(
                    requireFutureDate = true,
                    currentDate = ZonedDateTime.now().toCalendar()
                ) { _, timestamp ->
                    viewModel.modifyTaskCompletionDate(timestamp.toZonedDateTime()!!)
                }
                positiveButton(R.string.text_btn_done) {
                    viewModel.hasDisplayedCompletionDate(true)
                }
            }
        }

    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { renderState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: NewTaskState) {
        if (state.isSuccess) {
            showMessage("Task is saved successfully")
        }

        if (state.isFailure) {
            showMessage("Failed to save task")
        }

        if (state.isLoadedAttachment) {
            binding.cvAttachment.gone()
            binding.viewHolderAttachment.root.show()
            binding.viewHolderAttachment.tvAttachmentName.text =
                viewModel.currentState.attachmentTitle
        }

        if (state.isDisplayedSingleNote) {
            binding.cvNote.gone()
            binding.viewHolderNote.root.show()
        }

        binding.viewHolderNote.tvTitle.text = viewModel.currentState.noteTitle
        binding.viewHolderNote.tvDesc.text = viewModel.currentState.noteDesc
        binding.viewHolderNote.tvDate.text = viewModel.currentState.noteTimestamp

        if (state.isDisplayedGroup) {
            binding.btnSelectFolder.text = viewModel.currentState.taskGroupName
        }

        if (state.isDisplayedCompletionDate) {
            binding.btnSelectDateOfCompletion.text =
                viewModel.currentState.taskCompletionDate?.format(
                    DateTimeFormatter.asMonthAndWeek(requireContext())
                )
        }

        if (state.isDisplayedReminder) {
            binding.btnSelectReminder.text = viewModel.currentState.taskReminder?.format(
                DateTimeFormatter.asTime(
                    requireContext(),
                    true
                )
            )
            viewModel.hasDisplayedReminder(false)
        }



        showMessage("Current checked days: ${viewModel.currentState.scheduleDaysOfWeek}")

    }

    companion object {
        private const val INTENT_OPEN_DOCUMENT_TYPE = "*/*"
    }
}