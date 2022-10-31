package uz.behzod.eightytwenty.features.new_note

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.databinding.FragmentNoteGroupPickerBinding
import uz.behzod.eightytwenty.utils.extension.supportFragmentManager

@AndroidEntryPoint
class NoteGroupPickerFragment : BottomSheetDialogFragment() {

    companion object {
        private const val REQ_NAME_KEY = "NOTE_GROUP_PICKER_NAME_KEY"
        private const val REQ_NAME_VALUE = "NOTE_GROUP_PICKER_NAME_VALUE"
        private const val REQ_UID_KEY = "NOTE_GROUP_PICKER_UID_KEY"
        private const val REQ_UID_VALUE = "NOTE_GROUP_PICKER_UID_VALUE"
    }

    private var _binding: FragmentNoteGroupPickerBinding? = null
    private val binding: FragmentNoteGroupPickerBinding get() = _binding!!
    private val viewModel: NoteGroupPickerViewModel by viewModels()
    private lateinit var listener: NoteGroupPickerListener
    private lateinit var adapter: NoteGroupPickerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteGroupPickerBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val screen = BottomSheetDialog(requireContext(), theme)
        screen.setOnShowListener {
            val bottomSheet = it as BottomSheetDialog
            val parentLayout =
                bottomSheet.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { result ->
                val behavior = BottomSheetBehavior.from(result)
                renderHeight(result)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return screen
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupView() {
        adapter = NoteGroupPickerAdapter {
            viewModel.updateUid(it.uid)
            viewModel.updateName(it.name)
            listener.onClickListener()
            initRequests(it.name, it.uid)
            dismiss()
        }
        binding.rvNoteGroup.adapter = adapter
    }

    private fun initRequests(name: String, uid: Long) {
        supportFragmentManager.setFragmentResult(
            REQ_NAME_KEY, bundleOf(REQ_NAME_VALUE to name)
        )
        supportFragmentManager.setFragmentResult(
            REQ_UID_KEY, bundleOf(REQ_UID_VALUE to uid)
        )
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach(::renderState)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: NoteGroupPickerState) {
        if (state.onSuccess) {
            adapter.submitList(state.groups)
        }
    }

    private fun renderHeight(view: View) {
        val layoutParams = view.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        view.layoutParams = layoutParams
    }

    fun setOnClickListener(param: NoteGroupPickerListener) {
        listener = param
    }

}
