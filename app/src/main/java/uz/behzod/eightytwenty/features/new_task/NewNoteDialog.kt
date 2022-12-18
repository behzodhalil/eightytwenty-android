package uz.behzod.eightytwenty.features.new_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.databinding.DialogNewNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.supportFragmentManager
import java.time.ZonedDateTime

@AndroidEntryPoint
class NewNoteDialog : BottomSheetDialogFragment() {

    private var _binding: DialogNewNoteBinding? = null
    private val binding: DialogNewNoteBinding get() = _binding!!

    private val viewModel: NewNoteDialogViewModel by viewModels()

    private lateinit var closeListener: NewNoteListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogNewNoteBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        binding.tvDate.text = ZonedDateTime.now().toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        binding.btnSaveOrCancel.setOnClickListener {
            insertNote()
            dismiss()
        }
    }
    private fun insertNote() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.insertNote(createNote())
                viewModel.uiState.collect { state ->
                    when(state) {
                        NewNoteUiState.Empty -> {}
                        NewNoteUiState.Failure -> {}
                        NewNoteUiState.Loading -> {}
                        NewNoteUiState.Success -> {
                            closeListener.close()
                        }
                    }
                }
            }
        }
    }

    private fun createNote(): NoteDomainModel {
        val title = binding.etTitle.text.toString()
        val description = binding.etDesc.text.toString()
        val timestamp = ZonedDateTime.now()
        val isTrashed = false



        supportFragmentManager.setFragmentResult(
            "NoteTitle",
            bundleOf("NoteTitle" to title)
        )
        supportFragmentManager.setFragmentResult(
            "NoteDescription",
            bundleOf("NoteDescription" to description)
        )
        supportFragmentManager.setFragmentResult(
            "NoteTimestamp",
            bundleOf("NoteTimestamp" to timestamp.toString())
        )
        supportFragmentManager.setFragmentResult(
            "NoteIsTrashed",
            bundleOf("NoteIsTrashed" to isTrashed)
        )

        return NoteDomainModel(
            title = title,
            description = description,
            timestamp = timestamp,
            isTrashed = isTrashed
        )
    }

    interface NewNoteListener {
        fun close()
    }

    fun setCloseListener(listener: NewNoteListener) {
        closeListener = listener
    }
}
