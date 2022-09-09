package uz.behzod.eightytwenty.features.note_detail

import android.os.Bundle
import android.util.Log
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
    private lateinit var data: NoteDomainModel

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

                            Log.d("NoteDetailFragment", "Detail data is ${state.data}")
                            initNote(state.data)
                            data = state.data

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
            data.title = binding.etTitle.text.toString()
            data.description = binding.etDesc.text.toString()
            data.timestamp = ZonedDateTime.now()

            viewModel.updateNote(
                data
            ).run {
                Toast.makeText(requireContext(), "Note is updated", Toast.LENGTH_SHORT).show()
                val action = NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteFragment()
                Log.d("TAG", "Note detail value is ${getNotes()}")
                findNavController().navigate(action)
            }

        }

    }

    private fun initNote(data: NoteDomainModel) {
        with(binding) {
            etTitle.setText(data.title)
            etDesc.setText(data.description)
            tvDate.text = data.timestamp.toString()
        }
    }

    private fun getNotes(): NoteDomainModel {
        val title = binding.etTitle.text.toString()
        val description = binding.etDesc.text.toString()
        val timestamp = ZonedDateTime.now()
        return NoteDomainModel(title = title, description = description, timestamp = timestamp)

    }
}