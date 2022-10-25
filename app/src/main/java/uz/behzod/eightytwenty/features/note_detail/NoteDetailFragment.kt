package uz.behzod.eightytwenty.features.note_detail

import android.media.Image
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentNoteDetailBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.features.new_note.ImageAdapter
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.ZonedDateTime

@AndroidEntryPoint
class NoteDetailFragment : Fragment(R.layout.fragment_note_detail) {

    private val binding by viewBinding(FragmentNoteDetailBinding::bind)
    private val viewModel: NoteDetailViewModel by viewModels()
    private val args: NoteDetailFragmentArgs by navArgs()
    private lateinit var imageAdapter: ImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupView()
        observerState()
    }

    private fun setupUI() {
        val uid = args.noteId
        viewModel.fetchNoteRelationByUid(uid)
        //fetchNoteByUid(uid)
    }

    private fun setupView() {
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        imageAdapter = ImageAdapter()
        binding.rvImage.adapter = imageAdapter
    }
    
    private fun observerState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { renderState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: NoteDetailState) {
        val note = state.note

        note?.let {
            binding.etTitle.setText(it.note.title)
            binding.etDesc.setText(it.note.description)
            binding.tvDate.text = it.note.timestamp.toString()
            imageAdapter.submitList(it.images)
            printDebug { "[NoteDetailFragment]: Fetched images are ${it.images}" }

        }
    }

    private fun undoDescription() {
        binding.ivUndo.setOnClickListener {
            if (binding.etDesc.canUndo()) {
                binding.etDesc.undo()
            }
        }
    }

    private fun redoDescription() {
        binding.ivRedo.setOnClickListener {
            if (binding.etDesc.canRedo()) {
                binding.etDesc.redo()
            }
        }
    }

    private fun delete() {
        binding.ivDelete.setOnClickListener {
            binding.etTitle.setText("")
            binding.etDesc.setText("")
        }
    }

    private fun setUndoRedoListener() {

    }
}
