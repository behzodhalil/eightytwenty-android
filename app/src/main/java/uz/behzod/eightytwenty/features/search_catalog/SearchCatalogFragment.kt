package uz.behzod.eightytwenty.features.search_catalog

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
import uz.behzod.eightytwenty.databinding.FragmentSearchCatalogBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class SearchCatalogFragment: Fragment(R.layout.fragment_search_catalog) {

    private val binding by viewBinding(FragmentSearchCatalogBinding::bind)
    private val viewModel: SearchCatalogViewModel by viewModels()
    private lateinit var adapter: SearchCatalogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        onQueryCatalogListener()


    }

    private fun setupUI() {
        adapter = SearchCatalogAdapter()
        binding.rvCatalog.adapter = adapter
    }

    private fun onQueryCatalogListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchCatalog(newText)
                }
                return false
            }
        })
    }

    private fun searchCatalog(queryName: String) = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            val query = "%$queryName%"

            viewModel.searchCatalog(query)
            viewModel.uiState.collect { state ->
                when(state) {
                    SearchCatalogUiState.Empty -> {

                    }
                    is SearchCatalogUiState.Failure -> {

                    }
                    SearchCatalogUiState.Loading -> {

                    }
                    is SearchCatalogUiState.Success -> {
                        adapter.submitList(state.data)
                    }
                }
            }
        }

    }
}