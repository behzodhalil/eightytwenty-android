package uz.behzod.eightytwenty.features.new_task

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.databinding.DialogCatalogBinding

@AndroidEntryPoint
class CatalogDialog : BottomSheetDialogFragment() {

    private var _binding: DialogCatalogBinding? = null
    private val binding: DialogCatalogBinding get() = _binding!!
    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var adapter: CatalogAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        fetchCatalogs()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheet = it as BottomSheetDialog

            val parentLayout =
                bottomSheet.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { result ->
                val behavior = BottomSheetBehavior.from(result)
                setupHeight(result)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupUI() {
        adapter = CatalogAdapter()
        binding.rvCatalog.adapter = adapter
    }

    private fun setupHeight(v: View) {
        val layoutParams = v.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        v.layoutParams = layoutParams
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