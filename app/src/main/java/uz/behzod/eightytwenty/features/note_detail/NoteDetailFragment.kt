package uz.behzod.eightytwenty.features.note_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.databinding.FragmentNoteDetailBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import java.time.ZonedDateTime

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding: FragmentNoteDetailBinding get() = _binding!!
    private val viewModel: NoteDetailViewModel by viewModels()
    private val args: NoteDetailFragmentArgs by navArgs()
    private var data: NoteDomainModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val uid = args.noteId
        fetchNoteByUid(uid)

        onUpdate()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchNoteByUid(uid: Long) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchNoteByUid(uid)
                viewModel.uiState.collect { state ->
                    when (state) {
                        is NoteDetailUIState.Empty -> {

                        }
                        is NoteDetailUIState.Failure -> {

                        }
                        is NoteDetailUIState.Loading -> {

                        }
                        is NoteDetailUIState.Success -> {
                            data = state.data
                            initNote()
                        }
                    }
                }
            }
        }
    }

    private fun onUpdate() {
        binding.btnSaveOrCancel.setOnClickListener {
            updateNote()
        }
    }
    private fun updateNote() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.updateNote(
                    data!!.copy(
                        title = binding.etTitle.text.toString(),
                        description = binding.etDesc.text.toString(),
                        timestamp = ZonedDateTime.now()
                    )
                ).run {
                    Toast.makeText(requireContext(), "Note is updated", Toast.LENGTH_SHORT).show()
                    val action = NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteFragment()
                    findNavController().navigate(action)
                }
            }
        }

    }

    private fun initNote() {
        with(binding) {
            etTitle.setText(data?.title)
            etDesc.setText(data?.description)
            tvDate.text = data?.timestamp?.toString()
        }
    }
}