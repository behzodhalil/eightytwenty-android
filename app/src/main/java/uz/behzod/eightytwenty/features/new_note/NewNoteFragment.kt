package uz.behzod.eightytwenty.features.new_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentNewNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import java.time.ZonedDateTime

@AndroidEntryPoint
class NewNoteFragment : Fragment() {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding: FragmentNewNoteBinding get() = _binding!!
    private val viewModel: NewNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            viewModel.insertNote(categoryId = 1,createNote()).run {
                Toast.makeText(requireContext(),"Note is saved",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_newNoteFragment_to_noteFragment)
            }
        }

    }

    private fun createNote(): NoteDomainModel {
        val title = binding.etTitle.text.toString()
        val description = binding.etDesc.text.toString()
        val timestamp = ZonedDateTime.now()
        val isTrashed = false
        val categoryId = 1L

        return NoteDomainModel(
            title = title,
            description = description,
            timestamp = timestamp,
            isTrashed = isTrashed,
            categoryId = categoryId
        )
    }
}