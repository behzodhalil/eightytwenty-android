package uz.behzod.eightytwenty.features.select_productivity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.databinding.FragmentSelectTaskProductivityBinding
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class SelectTaskProductivityFragment : Fragment(R.layout.fragment_select_task_productivity) {

    private val binding: FragmentSelectTaskProductivityBinding by viewBinding(
        FragmentSelectTaskProductivityBinding::bind
    )
    private val viewModel: SelectTaskProductivityViewModel by viewModels()

    private lateinit var tasksNearTimeAdapter: TaskNearTimeProductivityAdapter
    private lateinit var taskRecentAdapter: TaskRecentProductivityAdapter
    private lateinit var taskAllAdapter: TaskAllProductivityAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

    }

    private fun setupView() {
        setupConcatAdapter()
        observeState()
    }

    private fun setupConcatAdapter() {
        initAdapters()
        if (::tasksNearTimeAdapter.isInitialized
            && ::taskRecentAdapter.isInitialized
            && ::taskAllAdapter.isInitialized
        ) {

            val concatAdapter =
                ConcatAdapter(tasksNearTimeAdapter,
                    taskRecentAdapter,
                    taskAllAdapter)

            binding.rvSelectTask.apply {
                adapter = concatAdapter
            }
        }
    }

    private fun initAdapters() {
        tasksNearTimeAdapter = TaskNearTimeProductivityAdapter()
        taskRecentAdapter = TaskRecentProductivityAdapter()
        taskAllAdapter = TaskAllProductivityAdapter()
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { renderState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: SelectTaskState) {
        printDebug { "[STPF]: renderState() ${state.tasksNearTime}" }
        printDebug { "[STPF]: renderState() ${state.tasksRecent}" }
        printDebug { "[STPF]: renderState() ${state.tasksLimited}" }
        if (state.tasksLimited.isNotEmpty()) {
            fetchTasksLimited(state.tasksLimited)
        }
        if (state.tasksRecent.isNotEmpty()) {
            fetchTasksRecent(state.tasksRecent)
        }
        if (state.tasksNearTime.isNotEmpty()) {
            fetchTasksNearTime(state.tasksNearTime)
        }
    }

    private fun fetchTasksNearTime(values: List<TaskEntity>) =
        tasksNearTimeAdapter.submitList(values)

    private fun fetchTasksRecent(values: List<TaskEntity>) {
        taskRecentAdapter.submitList(values)
    }

    private fun fetchTasksLimited(values: List<TaskEntity>) {
        taskAllAdapter.submitList(values)
    }
}
