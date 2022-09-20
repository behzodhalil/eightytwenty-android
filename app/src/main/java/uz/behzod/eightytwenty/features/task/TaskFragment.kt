package uz.behzod.eightytwenty.features.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentTaskBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class TaskFragment : Fragment(R.layout.fragment_task) {

    private val binding by viewBinding(FragmentTaskBinding::bind)
    private lateinit var adapter: TaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        onNavigateToNewTask()
        onNavigateToCatalog()
    }

    private fun setupUi() {
        adapter = TaskAdapter()
        binding.rvTask.adapter = adapter
        binding.rvTask.setHasFixedSize(true)
    }

    private fun onNavigateToNewTask() {
        binding.btnNewNote.setOnClickListener {
            val route = TaskFragmentDirections.actionTaskFragmentToNewTaskFragment()
            findNavController().navigate(route)
        }
    }

    private fun onNavigateToCatalog() {
        with(binding.ivFolder) {
            setOnClickListener {
                val route = TaskFragmentDirections.actionTaskFragmentToTaskCatalogFragment()
                findNavController().navigate(route)
            }
        }
    }

}