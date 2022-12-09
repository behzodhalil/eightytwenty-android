package uz.behzod.eightytwenty.features.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.sns.ui_expandable_view.ExpandableSelectionView
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.databinding.FragmentTaskBinding
import uz.behzod.eightytwenty.utils.extension.navController
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class TaskFragment : Fragment(R.layout.fragment_task) {

    private val binding by viewBinding(FragmentTaskBinding::bind)

    private lateinit var taskAdapter: TaskAdapter

    private lateinit var completeAdapter: TaskCompleteAdapter

    private val viewModel: TaskReduxViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        onNavigateToNewTask()
        onNavigateToCatalog()
        onNavigateToSearchTasks()

    }

    private fun setupView() {
        taskAdapter = TaskAdapter()
        binding.rvTask.adapter = taskAdapter

        completeAdapter = TaskCompleteAdapter(
            title = "Завершенный")

        binding.rvCompleteTasks.setAdapter(completeAdapter)
        binding.rvCompleteTasks.setState(ExpandableSelectionView.State.Expanded)
    }


    private fun onNavigateToNewTask() {
        binding.btnNewNote.setOnClickListener {
            binding.rvCompleteTasks.notifyDataChanged()
        }
    }

    private fun onNavigateToSearchTasks() {
        binding.ivSearch.setOnClickListener {
            route(TaskRoute.SearchTaskRoute)
        }
    }

    private fun onNavigateToCatalog() {
        with(binding.ivFolder) {
            setOnClickListener {
                route(TaskRoute.FolderRoute)
            }
        }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach {
                viewModel.fetchTasksByFolderUid()
                viewModel.fetchCompletedTasksByFolderUid()
                renderState(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: TaskState) {
        val tasks = state.tasks
        val completedTasks = state.completedTasks

        val completedCount = state.completedTasks.size

        if (state.isSuccess) {
            getTasks(tasks)
        }

        if (state.isCompleteSuccess) {
            getCompletedTasks(completedTasks)
            completeAdapter.setCount(completedCount)
        }

    }
    private fun route(route: TaskRoute) {
        when (route) {
            TaskRoute.FolderRoute -> {
                val direction = TaskFragmentDirections.actionTaskFragmentToTaskCatalogFragment()
                navController.navigate(direction)
                binding.rvCompleteTasks.notifyDataChanged()
            }
            TaskRoute.NewTaskRoute -> {
                val direction = TaskFragmentDirections.actionTaskFragmentToNewTaskFragment()
                navController.navigate(direction)
                completeAdapter.setData(listOf(TaskEntity(title = "Test #2")))
                binding.rvCompleteTasks.notifyDataChanged()
            }
            TaskRoute.SearchTaskRoute -> {
                val direction = TaskFragmentDirections.actionTaskFragmentToSearchTasksFragment()
                navController.navigate(direction)
            }
        }

    }

    private fun getTasks(tasks: List<TaskEntity>) {
        taskAdapter.submitList(tasks)
    }

    private fun getCompletedTasks(tasks: List<TaskEntity>) {
        completeAdapter.setData(tasks)
        binding.rvCompleteTasks.notifyDataChanged()
    }
}
