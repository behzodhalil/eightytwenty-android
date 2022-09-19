package uz.behzod.eightytwenty.features.new_task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.DialogCatalogBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class CatalogDialog : DialogFragment(R.layout.dialog_catalog) {

    private val binding by viewBinding(DialogCatalogBinding::bind)
    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var adapter: CatalogAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        fetchCatalogs()
    }

    private fun setupUI() {
        adapter = CatalogAdapter()
        binding.rvCatalog.adapter = adapter
    }


    private fun fetchCatalogs() = lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collect { state ->
                when (state) {
                    is CatalogUiState.Empty -> {

                    }
                    is CatalogUiState.Failure -> {

                    }
                    is CatalogUiState.Loading -> {

                    }
                    is CatalogUiState.Success -> {
                        adapter.submitList(state.data)
                    }
                }

            }
        }
    }


}