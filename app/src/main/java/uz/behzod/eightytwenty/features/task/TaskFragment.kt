package uz.behzod.eightytwenty.features.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentTaskBinding
import uz.behzod.eightytwenty.utils.extension.navController
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class TaskFragment : Fragment(R.layout.fragment_task) {

    private val binding by viewBinding(FragmentTaskBinding::bind)
    private lateinit var adapter: TaskAdapter
    private lateinit var completeAdapter: TaskCompleteAdapter
    private val viewModel: TaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        fetchTasks()

        onNavigateToNewTask()
        onNavigateToCatalog()
        onNavigateToSearchTasks()

    }

    private fun setupUi() {
        adapter = TaskAdapter()
        binding.rvTask.adapter = adapter
    }

    private fun setupView() {
        adapter = TaskAdapter()

        completeAdapter = TaskCompleteAdapter(emptyList(), title = "Завершенный", count = "5")
    }


    private fun onNavigateToNewTask() {
        binding.btnNewNote.setOnClickListener {
            val route = TaskFragmentDirections.actionTaskFragmentToNewTaskFragment()
            navController.navigate(route)
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
                val route = TaskFragmentDirections.actionTaskFragmentToTaskCatalogFragment()
                navController.navigate(route)
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
                    adapter.submitList(state.data)
                }
            }
        }
    }



    private fun route(route: TaskRoute) {
        when(route) {
            TaskRoute.FolderRoute -> {

            }
            TaskRoute.NewTaskRoute -> {

            }
            TaskRoute.SearchTaskRoute -> {
                val direction = TaskFragmentDirections.actionTaskFragmentToSearchTasksFragment()
                navController.navigate(direction)
            }
        }

    }

}