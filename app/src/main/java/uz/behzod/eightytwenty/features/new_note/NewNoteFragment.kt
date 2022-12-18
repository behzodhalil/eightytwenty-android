package uz.behzod.eightytwenty.features.new_note

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.databinding.FragmentNewNoteBinding
import uz.behzod.eightytwenty.utils.extension.*
import uz.behzod.eightytwenty.utils.helper.BitmapHelper
import uz.behzod.eightytwenty.utils.view.viewBinding
import uz.behzod.undo_redo.UndoEditText
import uz.behzod.undo_redo.UndoStateListener
import uz.behzoddev.ui_toast.errorMessage
import uz.behzoddev.ui_toast.successMessage
import java.time.ZonedDateTime

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note), AttachImageListeners {

    companion object {
        private const val REQ_NAME_KEY = "NOTE_GROUP_PICKER_NAME_KEY"
        private const val REQ_NAME_VALUE = "NOTE_GROUP_PICKER_NAME_VALUE"
        private const val REQ_UID_KEY = "NOTE_GROUP_PICKER_UID_KEY"
        private const val REQ_UID_VALUE = "NOTE_GROUP_PICKER_UID_VALUE"
    }

    private val binding by viewBinding(FragmentNewNoteBinding::bind)

    private val viewModel: NewNoteViewModel by viewModels()

    private val args: NewNoteFragmentArgs by navArgs()

    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter()
    }

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { state -> onTakePictureListener(state) }

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.OpenMultipleDocuments(),
        PickImagesCallbacks(this)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observerState()

        initImagePicker()
        initMaxHistorySize()

        setUndoRedoListener()

        onUndoDescription()
        onRedoDescription()
        onRemoveTitleAndDesc()
        onMoveToGroup()
        onSaveOrCancel()
        onTakeGroupListener()
    }

    private fun setupView() {
        setupRecyclerView()

        binding.tvDate.text = ZonedDateTime.now().toString()

        binding.etTitle.addTextChangedListener { viewModel.modifyTitle(it.asStringOrEmpty()) }
        binding.etDesc.addTextChangedListener { viewModel.modifyDesc(it.asStringOrEmpty()) }
        binding.tvDate.addTextChangedListener {
            viewModel.modifyTimestamp(
                it.asStringOrEmpty().asZoneDateTime()
            )
        }

        viewModel.modifyIsTrashed(false)

        if (args.categoryId==0L) {
            viewModel.modifyGroupUid(1L)
        } else {
            viewModel.modifyGroupUid(args.categoryId)
        }
    }

    private fun setupRecyclerView() {
        binding.rvImage.adapter = imageAdapter
    }

    private fun observerState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private suspend fun renderState(state: NewNoteViewState) {
        if (state.isSuccess) {
            if (navController.currentDestination?.id == R.id.newNoteFragment) {
                val route = NewNoteFragmentDirections.actionNewNoteFragmentToNoteFragment()
                navigateTo(route)
            }
        }

        if (state.isFailure) {
            showMessage("Note is empty")
        }

    }

    private fun initImagePicker() {
        binding.ivImage.setOnClickListener {
            val screen = ImagePickerFragment().apply {
                setTakePictureListener {
                    onTakePicture()
                }
                setPickFromGalleryListener {
                    pickImageLauncher.launch(arrayOf("image/*"))
                    printDebug { "Pick image launcher is launched." }
                }
            }
            transaction(screen)
        }
    }

    private fun initMaxHistorySize() {
        binding.etDesc.setMaxHistorySize(UndoEditText.HISTORY_INFINITE)
    }

    private fun setUndoRedoListener() {
        binding.ivUndo.isEnabled = true
        binding.ivRedo.isEnabled = true

        binding.etDesc.setUndoStatusListener(object : UndoStateListener {

            override fun onUndoStatusChanged(canUndo: Boolean) {
                binding.ivUndo.isEnabled = if (canUndo) {
                    binding.ivUndo.drawable(R.drawable.bg_oval_undo_redo_fill)
                    true
                } else {
                    binding.ivUndo.drawable(R.drawable.bg_oval_undo_redo)
                    false
                }
            }

            override fun onRedoStatusChanged(canRedo: Boolean) {
                binding.ivRedo.isEnabled = if (canRedo) {
                    binding.ivRedo.drawable(R.drawable.bg_oval_undo_redo_fill)
                    true
                } else {
                    binding.ivRedo.drawable(R.drawable.bg_oval_undo_redo)
                    false
                }
            }
        })

    }

    private fun onTakePicture() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val uri = BitmapHelper.saveImage(requireContext())
            viewModel.modifyUri(uri)
            takePictureLauncher.launch(viewModel.currentState.uri)
        }

    }

    override fun addImages(uriSource: List<Uri>) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            uriSource.forEach {
                viewModel.currentState.images += listOf(
                    NoteImageEntity(
                        uri = it,
                        imageUid = NoteImageEntity.generateUid()
                    )
                )
            }
            imageAdapter.submitList(viewModel.currentState.images)
        }
    }

    private fun onTakePictureListener(state: Boolean) {
        val tempUri = viewModel.currentState.uri
        if (state && tempUri != null) {
            addImages(listOf(tempUri))
            successMessage("Photo was taken")
        } else {
            errorMessage("No photo was taken")
        }
    }


    private fun onUndoDescription() {
        binding.ivUndo.setOnClickListener {
            if (binding.etDesc.canUndo()) {
                binding.etDesc.undo()
            }
        }
    }

    private fun onRedoDescription() {
        binding.ivRedo.setOnClickListener {
            if (binding.etDesc.canRedo()) {
                binding.etDesc.redo()
            }
        }
    }

    private fun onRemoveTitleAndDesc() {
        binding.ivDelete.setOnClickListener {
            binding.etDesc.setText("")
            binding.etTitle.setText("")
        }
    }

    private fun onSaveOrCancel() {
        binding.btnSaveOrCancel.setOnClickListener {
            viewModel.insertNote()
        }
    }

    private fun onTakeGroupListener() {
        supportFragmentManager.setFragmentResultListener(
            REQ_UID_KEY, viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getLong(REQ_UID_VALUE)
            viewModel.modifyGroupUid(result)
        }
    }

    private fun onMoveToGroup() {
        binding.ivMoveToGroup.setOnClickListener {
            val screen = NoteGroupPickerFragment()
            transaction(screen)
            screen.setOnClickListener(object: NoteGroupPickerListener {
                override fun onClickListener() {
                    successMessage("You taken the ${viewModel.currentState.categoryUid}")
                }
            })
        }
    }

}
