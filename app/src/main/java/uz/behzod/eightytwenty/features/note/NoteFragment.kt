package uz.behzod.eightytwenty.features.note

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.databinding.FragmentNoteBinding
import uz.behzod.eightytwenty.utils.extension.gone
import uz.behzod.eightytwenty.utils.extension.navigateTo
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.extension.show
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class NoteFragment : Fragment(R.layout.fragment_note) {

    private val binding by viewBinding(FragmentNoteBinding::bind)

    private val viewModel: NoteWithReduxViewModel by viewModels()

    private val args: NoteFragmentArgs by navArgs()

    private lateinit var adapter: NoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(it.note.id)
            findNavController().navigate(action)
        }
        binding.rvNote.adapter = adapter
    }

    private fun onNavigateNewNote() {
        binding.btnNewNote.setOnClickListener {
            route(NoteRoute.AddNote)
        }
    }

    private fun onNavigateToCategory() {
        binding.ivFolder.setOnClickListener {
            route(NoteRoute.NoteFolder)
        }
    }

    private fun onNavigateToSearchNotes() {
        binding.ivSearch.setOnClickListener {
            route(NoteRoute.SearchNote)
        }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                if (args.categoryId == 0L) {
                    viewModel.updateGroupUid(1L)
                } else {
                    viewModel.updateGroupUid(args.categoryId)
                }

                viewModel.fetchNotesByUid()

                renderState(state)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private suspend fun renderState(state: NoteViewState) {

        if(state.isEmpty) {
            printDebug { "State is empty by uid" }
            binding.tvEmpty.show()
            binding.lawEmpty.show()
        }

        if (state.isSuccess) {
            withContext(Dispatchers.IO) {
                binding.lawEmpty.gone()
                binding.tvEmpty.gone()
            }
            getNotes(state.notes)
        }

    }

    private fun getNotes(notes: List<NoteRelation>) = adapter.submitList(notes)

    private fun route(route: NoteRoute) {
        when(route) {
            NoteRoute.AddNote -> {
                if (findNavController().currentDestination?.id == R.id.noteFragment) {
                    val direction = NoteFragmentDirections.actionNoteFragmentToNewNoteFragment()
                    navigateTo(direction)
                }
            }
            NoteRoute.NoteFolder -> {
                if (findNavController().currentDestination?.id == R.id.noteFragment) {
                    val direction = NoteFragmentDirections.actionNoteFragmentToCategoryNoteFragment()
                    navigateTo(direction)
                }
            }
            NoteRoute.SearchNote -> {
                if (findNavController().currentDestination?.id == R.id.noteFragment) {
                    val direction = NoteFragmentDirections.actionNoteFragmentToSearchNotesFragment()
                    navigateTo(direction)
                }
            }
            NoteRoute.DetailNote -> {
                if (findNavController().currentDestination?.id == R.id.noteFragment) {
                    val direction = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment()
                    navigateTo(direction)
                }
            }
        }
    }
}

