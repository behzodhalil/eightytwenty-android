package uz.behzod.eightytwenty.features.category_note

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentCategoryNoteBinding
import uz.behzod.eightytwenty.utils.extension.*
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class CategoryNoteFragment : Fragment(R.layout.fragment_category_note) {

    private val binding by viewBinding(FragmentCategoryNoteBinding::bind)

    private val viewModel: CategoryViewModel by viewModels()

    private lateinit var adapter: CategoryNoteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        observeState()

        onSaveOrCancel()
        onNavigateNote()
        onNavigateToSearchNote()

        swipeToDelete()
    }

    private fun setupView() {
        setupRecyclerView()

        binding.etNewCategory.addTextChangedListener { name -> viewModel.updateCategoryName(name.asStringOrEmpty()) }
    }

    private fun setupRecyclerView() {
        adapter = CategoryNoteAdapter {
            val action = CategoryNoteFragmentDirections.actionCategoryNoteFragmentToNoteFragment(
                it.uid,
                it.name
            )
            findNavController().navigate(action)
        }
        binding.rvNoteCategories.adapter = adapter
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: CategoryState) {
        val isSaved = state.onSaved
        val isSuccess = state.onSuccess
        val isDeleted = state.onDeleted

        if (isSaved) {
            showMessage("Category is successfully saved")
            binding.btnNewCategory.gone()
            binding.btnNewSubCategory.gone()
            binding.btnCancel.gone()
            binding.btnNewCategoryNote.show()
            binding.etNewCategory.gone()
            binding.btnLastSave.gone()
            binding.etNewCategory.text.clear()
            viewModel.updateCategoryName("")
            viewModel.hasSaved(false)
        }

        if (isSuccess) {
            adapter.submitList(state.categories)
        }


        if (isDeleted) {
            showMessage("Category is successfully deleted")
            viewModel.hasDeleted(false)
        }
    }


    private fun onNavigateNote() {
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_categoryNoteFragment_to_noteFragment)
        }
    }

    private fun onNavigateToSearchNote() {
        binding.ivSearch.setOnClickListener {
            val action =
                CategoryNoteFragmentDirections.actionCategoryNoteFragmentToSearchNotesFragment()
            navigateTo(action)
        }
    }


    private fun onSaveOrCancel() {
        binding.btnNewCategoryNote.setOnClickListener {
            printDebug { "New category note is clicked" }
            binding.btnNewCategory.show()
            binding.btnNewSubCategory.show()
            binding.btnCancel.show()
            binding.btnNewCategoryNote.hide()
        }

        binding.btnNewCategory.setOnClickListener {
            printDebug { "New category is clicked" }
            binding.etNewCategory.show()
            binding.btnLastSave.show()
        }

        binding.btnLastSave.setOnClickListener {
            viewModel.insertCategory()
        }
        binding.btnCancel.setOnClickListener {

        }
    }

    private fun swipeToDelete() {
        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.absoluteAdapterPosition
                    val category = adapter.currentList[position]

                    viewModel.updateCategoryName(category.name)
                    viewModel.updateUid(category.uid)

                    viewModel.deleteCategory()
                }
            }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvNoteCategories)
        }
    }

}
