package uz.behzod.eightytwenty.features.category_note

import android.os.Bundle
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
import uz.behzod.eightytwenty.databinding.FragmentCategoryNoteBinding
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel
import uz.behzod.eightytwenty.utils.ext.gone
import uz.behzod.eightytwenty.utils.ext.hide
import uz.behzod.eightytwenty.utils.ext.show

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
        _binding = FragmentCategoryNoteBinding.inflate(inflater, container, false)
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
        initRecyclerView()

        fetchCategories()

        onDoNewCategory()
        onDoCancel()
        onAddNewCategory()

        onNavigateNote()
        onNavigateNotesByName()
    }

    private fun initRecyclerView() {
        adapter = CategoryNoteAdapter {
            val action = CategoryNoteFragmentDirections.actionCategoryNoteFragmentToNoteFragment(it.id,it.name)
            findNavController().navigate(action)
        }
        binding.rvNoteCategories.adapter = adapter
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

    private fun onDoNewCategory() {
        binding.btnNewCategoryNote.setOnClickListener {
            binding.btnNewCategory.show()
            binding.btnNewSubCategory.show()
            binding.btnCancel.show()
            binding.btnNewCategoryNote.hide()
        }
    }

    private fun onDoCancel() {
        binding.btnCancel.setOnClickListener {

        }
    }

    private fun onAddNewCategory() {
        binding.btnNewCategory.setOnClickListener {
            binding.etNewCategory.show()
            binding.btnNewCategory.setOnClickListener {
                onInsertNewCategory().run {
                    Toast.makeText(
                        requireContext(),
                        "Category is successfully saved",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.btnNewCategory.hide()
                    binding.btnNewSubCategory.hide()
                    binding.btnCancel.hide()
                    binding.btnNewCategoryNote.show()
                    binding.etNewCategory.gone()
                }

            }
        }
    }

    private fun onInsertNewCategory() {
        lifecycleScope.launch {
            viewModel.insertNoteCategory(
                NoteCategoryDomainModel(name = binding.etNewCategory.text.toString())
            )
        }
    }

    private fun fetchCategories() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { result ->
                    when (result) {
                        is CategoryNoteUIState.Empty -> {

                        }
                        is CategoryNoteUIState.Failure -> {

                        }
                        is CategoryNoteUIState.Loading -> {

                        }
                        is CategoryNoteUIState.Success -> {
                            adapter.submitList(result.data)
                        }
                    }
                }
            }
        }
    }
}