package uz.behzod.eightytwenty.features.search_tasks

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentSearchTasksBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class SearchTasksFragment : Fragment(R.layout.fragment_search_tasks) {

    private val binding by viewBinding(FragmentSearchTasksBinding::bind)
    private val viewModel: SearchTasksViewModel by viewModels()
    private lateinit var adapter: SearchTasksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()

        searchTaskListener()
    }

    private fun setupUi() {
        adapter = SearchTasksAdapter()
        binding.rvNote.adapter = adapter
    }

    private fun searchTaskListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchAndGetTasks(it)
                }
                return false
            }
        })
    }
    private fun searchAndGetTasks(name: String) {
        lifecycleScope.launch {

            val query = "%$name%"
                viewModel.searchAndGetTasks(query)

                viewModel.uiState.collect { state ->
                    when(state) {
                        is SearchTasksUiState.Empty -> {

                        }
                        is SearchTasksUiState.Failure -> {

                        }
                        is SearchTasksUiState.Loading -> {

                        }
                        is SearchTasksUiState.Success -> {
                            adapter.submitList(state.tasks)
                        }
                    }
                }
        }
    }
}