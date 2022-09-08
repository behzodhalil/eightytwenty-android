package uz.behzod.eightytwenty.features.category_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentCategoryNoteBinding

@AndroidEntryPoint
class CategoryNoteFragment : Fragment() {

    private var _binding: FragmentCategoryNoteBinding? = null
    private val binding: FragmentCategoryNoteBinding get() = _binding!!
    private val viewModel: CategoryNoteViewModel by viewModels()
    private lateinit var adapter: CategoryNoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryNoteBinding.inflate(inflater,container,false)
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
        onNavigateNote()
        onNavigateNotesByName()
    }

    private fun onNavigateNote() {
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_categoryNoteFragment_to_noteFragment)
        }
    }

    private fun onNavigateNotesByName() {

    }

    private fun onNavigateToSearchNote() {

    }
}