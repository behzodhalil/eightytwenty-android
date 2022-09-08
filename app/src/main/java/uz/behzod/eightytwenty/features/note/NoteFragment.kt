package uz.behzod.eightytwenty.features.note

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentNoteBinding

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()

    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated() is created")
        setupUI()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        initAdapter()

        fetchNotes()

        onNavigateNewNote()
        onNavigateToCategory()
    }

    private fun initAdapter() {
        adapter = NoteAdapter()
        binding.rvNote.adapter = adapter
    }

    private fun fetchNotes() = lifecycleScope.launch {
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
    }

    private fun onNavigateNewNote() {
        binding.btnNewNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_newNoteFragment)
        }
    }

    private fun onNavigateToCategory() {
        binding.ivFolder.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_categoryNoteFragment)
        }
    }

    companion object {
        private const val TAG = "NoteFragment"
    }
}