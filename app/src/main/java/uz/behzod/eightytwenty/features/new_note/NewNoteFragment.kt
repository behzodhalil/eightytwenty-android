package uz.behzod.eightytwenty.features.new_note

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentNewNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.debugger
import uz.behzod.eightytwenty.utils.extension.navController
import uz.behzod.eightytwenty.utils.extension.supportFragmentManager
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.ZonedDateTime

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private val binding by viewBinding(FragmentNewNoteBinding::bind)
    private val viewModel: NewNoteViewModel by viewModels()
    private val args: NewNoteFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        initTimestamp()
        insertNote()
    }

    private fun initTimestamp() {
        binding.tvDate.text = ZonedDateTime.now().toString()
    }

    private fun insertNote() {
        binding.btnSaveOrCancel.setOnClickListener {
            viewModel.insertNote(createNote()).run {
                Toast.makeText(requireContext(), "Note is saved", Toast.LENGTH_SHORT).show()
                if (navController.previousBackStackEntry?.destination?.id == R.id.noteFragment) {
                    if (navController.currentDestination?.id == R.id.newNoteFragment) {
                        findNavController().navigate(R.id.action_newNoteFragment_to_noteFragment)
                    }

                }
                if (navController.previousBackStackEntry?.destination?.id == R.id.newTaskFragment) {
                    if (navController.currentDestination?.id == R.id.newNoteFragment) {
                        findNavController().navigate(R.id.action_newNoteFragment_to_newTaskFragment)
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
        val categoryId = args.categoryId

        return NoteDomainModel(
            title = title,
            description = description,
            timestamp = timestamp,
            isTrashed = isTrashed,
            categoryId = categoryId
        )
    }

    private fun setNoteForTask() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDesc.text.toString()

        supportFragmentManager.setFragmentResult(
            "NoteTitle",
            bundleOf("NoteTitle" to title)
        )
    }


}