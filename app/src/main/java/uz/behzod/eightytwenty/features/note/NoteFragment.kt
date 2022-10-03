package uz.behzod.eightytwenty.features.note

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.databinding.FragmentNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class NoteFragment : Fragment(R.layout.fragment_note) {

    private val binding by viewBinding(FragmentNoteBinding::bind)
    private val viewModel: NoteWithReduxViewModel by viewModels()
    private val args: NoteFragmentArgs by navArgs()
    private lateinit var adapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated() is created")
        setupUI()
        observeState()

    }

    private fun setupUI() {
        initAdapter()

        onNavigateNewNote()
        onNavigateToCategory()
        onNavigateToSearchNotes()
    }

    private fun initAdapter() {
        adapter = NoteAdapter {
            val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(it.id)
            findNavController().navigate(action)
        }
        binding.rvNote.adapter = adapter
    }

    private fun onInitializerById(value: Long) {
        if (value == 0L) {
            Log.d("NoteFragment","Category identifier is $value")
            observeState()
        } else {
            Log.d("NoteFragment","Category identifier is $value")
            // fetchNotesById(value)
        }
    }
    /*private fun fetchNotesById(value: Long) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchNotesByCategoryId(value)
                viewModel.uiStateById.collect { state ->
                    when(state) {
                        is NoteUIState.Empty -> {

                        }
                        is NoteUIState.Failure -> {

                        }
                        is NoteUIState.Loading -> {

                        }
                        is NoteUIState.Success -> {
                            adapter.submitList(state.data)
                        }
                    }
                }
            }
        }
    }*/

    /*private fun fetchNotes() = lifecycleScope.launch {
        Log.d(TAG,"fetchNotes is created")
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiState.collect { state ->
                when(state) {
                    is NoteUIState.Empty -> {
                        Log.d("NoteFragment","Note is Empty")
                    }
                    is NoteUIState.Failure -> {
                        Toast.makeText(requireContext(),state.message,Toast.LENGTH_SHORT).show()
                    }
                    is NoteUIState.Loading -> {

                    }
                    is NoteUIState.Success -> {
                        Log.d("NoteFragment","List is ${state.data}")
                        adapter.submitList(state.data)
                    }
                }
            }
        }
    }*/

    private fun onNavigateNewNote() {
        binding.btnNewNote.setOnClickListener {
            val action = NoteFragmentDirections.actionNoteFragmentToNewNoteFragment(args.categoryId)
            findNavController().navigate(action)
        }
    }

    private fun onNavigateToCategory() {
        binding.ivFolder.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_categoryNoteFragment)
        }
    }

    private fun onNavigateToSearchNotes() {
        binding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_searchNotesFragment)
        }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle,Lifecycle.State.STARTED)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: NoteViewState) {
        val notes = state.notes

        if (notes.isNotEmpty()) {
            getNotes(notes)
        }
    }

    private fun getNotes(notes: List<NoteDomainModel>) = adapter.submitList(notes)

    companion object {
        private const val TAG = "NoteFragment"
    }
}