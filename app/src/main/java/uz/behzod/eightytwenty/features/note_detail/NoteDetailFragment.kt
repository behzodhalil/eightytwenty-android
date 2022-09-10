package uz.behzod.eightytwenty.features.note_detail

import android.os.Bundle
import android.util.Log
import android.view.View
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
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentNoteDetailBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.ZonedDateTime

@AndroidEntryPoint
class NoteDetailFragment : Fragment(R.layout.fragment_note_detail) {

    private val binding by viewBinding(FragmentNoteDetailBinding::bind)
    private val viewModel: NoteDetailViewModel by viewModels()
    private val args: NoteDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val uid = args.noteId
        fetchNoteByUid(uid)
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
                            val data = state.data
                            Log.d("NoteDetailFragment", "Detail data is ${state.data}")

                            initNote(data)
                            updateNote(param = data)

                        }
                    }
                }
            }
        }
    }


    private fun updateNote(param: NoteDomainModel) {
        lifecycleScope.launch {
            binding.btnSaveOrCancel.setOnClickListener {
                param.title = binding.etTitle.text.toString()
                param.description = binding.etDesc.text.toString()
                param.timestamp = ZonedDateTime.now()

                viewModel.updateNote(
                    param
                ).run {
                    Toast.makeText(requireContext(), "Note is updated", Toast.LENGTH_SHORT).show()
                    val action =
                        NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteFragment()
                    Log.d("TAG", "Note detail value is ${getNotes()}")
                    findNavController().navigate(action)
                }
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