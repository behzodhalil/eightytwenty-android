package uz.behzod.eightytwenty.features.search_notes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentSearchNotesBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class SearchNotesFragment : Fragment(R.layout.fragment_search_notes) {

    private val binding by viewBinding(FragmentSearchNotesBinding::bind)
    private val viewModel: SearchNotesViewModel by viewModels()

    private lateinit var adapter: SearchNotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()

        search()
    }


    private fun setupUi() {
        adapter = SearchNotesAdapter()
        binding.rvNote.adapter = adapter
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchNotes(newText)
                }
                return false
            }
        })
    }

    private fun searchNotes(query: String) = lifecycleScope.launch {
        val searchQuery = "%$query%"

        viewModel.searchNotes(searchQuery)
        viewModel.uiState.collect { result ->
            when (result) {
                is SearchNotesUIState.Empty -> {

                }
                is SearchNotesUIState.Failure -> {

                }
                is SearchNotesUIState.Loading -> {

                }
                is SearchNotesUIState.Success -> {
                    adapter.submitList(result.data)
                    Log.d("","Result data is ${result.data}")
                }
            }
        }
    }
}