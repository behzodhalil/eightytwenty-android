package uz.behzod.eightytwenty.features.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
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
    private val viewModel: TaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        fetchTasks()

        onNavigateToNewTask()
        onNavigateToCatalog()
        onNavigateToSearchTasks()

    }

    private fun setupView() {
        taskAdapter = TaskAdapter()
        binding.rvTask.adapter = taskAdapter
        completeAdapter = TaskCompleteAdapter(
            title = "Завершенный",
            counter = "5")
        binding.rvCompleteTasks.setAdapter(completeAdapter)
        binding.rvCompleteTasks.setState(ExpandableSelectionView.State.Expanded)
        completeAdapter.setData(listOf(
            TaskEntity(title = "Test 1")
        ))
    }


    private fun onNavigateToNewTask() {
        binding.btnNewNote.setOnClickListener {
            completeAdapter.setData(listOf(
                TaskEntity(title = "Test 2")
            ))
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

    private fun fetchTasks() = lifecycleScope.launchWhenCreated {
        viewModel.uiState.collect { state ->
            when (state) {
                is TaskUiState.Empty -> {

                }
                is TaskUiState.Failure -> {

                }
                is TaskUiState.Loading -> {

                }
                is TaskUiState.Success -> {
                    taskAdapter.submitList(state.data)
                    printDebug { "[TaskFragment]: Tasks are ${state.data}" }
                }
            }
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

}