package uz.behzod.eightytwenty.features.category_note

import android.os.Bundle
import android.view.View
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
import uz.behzod.eightytwenty.utils.extension.gone
import uz.behzod.eightytwenty.utils.extension.hide
import uz.behzod.eightytwenty.utils.extension.show
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class CategoryNoteFragment : Fragment(R.layout.fragment_category_note) {

    private val binding by viewBinding(FragmentCategoryNoteBinding::bind)
    private val viewModel: CategoryNoteViewModel by viewModels()
    private lateinit var adapter: CategoryNoteAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        initRecyclerView()

        fetchCategories()

        onDoNewCategory()
        onDoCancel()
        onAddNewCategory()

        onNavigateNote()
        onNavigateNotesByName()
        onNavigateToSearchNote()
    }

    private fun initRecyclerView() {
        adapter = CategoryNoteAdapter {
            val action = CategoryNoteFragmentDirections.actionCategoryNoteFragmentToNoteFragment(
                it.uid,
                it.name
            )
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
        binding.ivSearch.setOnClickListener {
            val action =
                CategoryNoteFragmentDirections.actionCategoryNoteFragmentToSearchNotesFragment()
            findNavController().navigate(action)
        }
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