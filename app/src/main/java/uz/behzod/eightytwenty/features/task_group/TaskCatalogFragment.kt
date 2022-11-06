package uz.behzod.eightytwenty.features.task_group

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.databinding.FragmentCatalogTaskBinding
import uz.behzod.eightytwenty.utils.extension.*
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class TaskCatalogFragment : Fragment(R.layout.fragment_catalog_task) {

    private val binding by viewBinding(FragmentCatalogTaskBinding::bind)
    private val viewModel: TaskGroupViewModel by viewModels()

    private lateinit var adapter: TaskCatalogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeState()
        onClickNewCatalog()
        onNavigateToTask()
        onNavigateToSearchTask()

    }

    private fun setupUI() {
        adapter = TaskCatalogAdapter()
        binding.rvTaskCatalogs.adapter = adapter
        binding.etNewCategory.addTextChangedListener { viewModel.modifyTaskGroupName(it.asStringOrEmpty()) }
    }

    private fun onNavigateToTask() {
        with(binding.ivBack) {
            setOnClickListener {
                val route = TaskCatalogFragmentDirections.actionTaskCatalogFragmentToTaskFragment()
                navController.navigate(route)
            }
        }
    }

    private fun onNavigateToSearchTask() {
        binding.ivSearch.setOnClickListener {
            val route =
                TaskCatalogFragmentDirections.actionTaskCatalogFragmentToSearchCatalogFragment()
            navController.navigate(route)
        }
    }

    private fun onClickNewCatalog() {
        binding.btnNewCategoryTask.setOnClickListener {
            binding.btnNewCategory.show()
            binding.btnNewSubTask.show()
            binding.btnCancel.show()
            binding.btnNewCategoryTask.hide()
        }

        binding.btnNewCategory.setOnClickListener {
            binding.etNewCategory.show()
            binding.btnNewCategory.setOnClickListener {
                viewModel.insertTaskGroup()
            }
        }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { renderState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: TaskGroupState) {
        val groups = state.taskGroups

        if (state.isAdded) {
            binding.btnNewCategory.hide()
            binding.btnNewSubTask.hide()
            binding.btnCancel.hide()
            binding.btnNewCategoryTask.show()
            binding.etNewCategory.gone()
        }

        if (state.isSuccess) {
            getTaskGroups(groups)
        }

    }

    private fun getTaskGroups(value: List<TaskCatalogEntity>) = adapter.submitList(value)

}