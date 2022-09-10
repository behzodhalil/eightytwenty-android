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
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentNoteBinding

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()
    private val args: NoteFragmentArgs by navArgs()
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
        val id = args.categoryId
        val name = args.categoryName
        if (name.isEmpty()) {
        } else {
            binding.tvTitleCategory.text = name
        }

        onInitializerById(value = id)
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
            fetchNotes()
        } else {
            Log.d("NoteFragment","Category identifier is $value")
            fetchNotesById(value)
        }
    }
    private fun fetchNotesById(value: Long) {
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

    companion object {
        private const val TAG = "NoteFragment"
    }
}