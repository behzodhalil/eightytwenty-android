package uz.behzod.eightytwenty.features.new_note

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import uz.behzod.undo_redo.UndoStatusListener
import java.time.ZonedDateTime

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note), AttachImageListeners {

    private val binding by viewBinding(FragmentNewNoteBinding::bind)
    private val viewModel: NewNoteWithReduxViewModel by viewModels()
    private val args: NewNoteFragmentArgs by navArgs()
    private var images: ArrayList<NoteImageEntity> = arrayListOf()
    private var uriSources: ArrayList<Uri> = arrayListOf()
    private lateinit var imageAdapter: ImageAdapter

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
        undo()
        redo()
    }

    private fun setupView() {
        imageAdapter = ImageAdapter()
        binding.rvImage.adapter = imageAdapter

        binding.tvDate.text = ZonedDateTime.now().toString()

        binding.etTitle.addTextChangedListener { viewModel.modifyTitle(it.asStringOrEmpty()) }
        binding.etDesc.addTextChangedListener { viewModel.modifyDesc(it.asStringOrEmpty()) }
        binding.tvDate.addTextChangedListener {
            viewModel.modifyTimestamp(
                it.asStringOrEmpty().asZoneDateTime()
            )
        }

        viewModel.modifyIsTrashed(false)

        viewModel.modifyGroupUid(args.categoryId)

        binding.btnSaveOrCancel.setOnClickListener {
            viewModel.insertNote()
        }

        binding.ivRedo.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            if (binding.etDesc.canRedo()) {
                binding.etDesc.redo()
            }
        }

        binding.ivUndo.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            printDebug { "Undo is clicked" }
            if (binding.etDesc.canUndo()) {
               binding.etDesc.undo()
            }
        }

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

        binding.etDesc.setMaxHistorySize(UndoEditText.HISTORY_INFINITE)

        setUndoRedoListener()

    }

    private fun observerState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: NewNoteViewState) {
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

    private fun onTakePicture() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val uri = BitmapHelper.saveImage(requireContext())
            viewModel.modifyUri(uri)
            takePictureLauncher.launch(viewModel.currentState.uri)
        }

    }

    override fun addImages(uriSource: List<Uri>) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            printDebug { "[NewNoteFragment]: addImages() is started" }
            uriSource.forEach {
                viewModel.currentState.images += listOf(NoteImageEntity(uri = it, imageUid = NoteImageEntity.generateUid() ))
            }
            imageAdapter.submitList(viewModel.currentState.images)
            printDebug { "[Test Image] Images are ${viewModel.currentState.images}" }

        }
    }

    private fun onPickFromGallery() {}
    private fun onPickFromGalleryListener() {}

    private fun onTakePictureListener(state: Boolean) {
        val tempUri = viewModel.currentState.uri
        if (state && tempUri != null) {
            addImages(listOf(tempUri))
            showMessage("Photo was taken")
        } else {
            showMessage("No photo was taken")
        }
    }

    private fun undo() {
        printDebug { "undo() method is called" }

    }

    private fun redo() {

    }

    private fun setUndoRedoListener() {


        binding.etDesc.setUndoStatusListener(object: UndoStatusListener {

            override fun onUndoStatusChanged(canUndo: Boolean) {
                binding.ivUndo.isEnabled = canUndo
            }

            override fun onRedoStatusChanged(canRedo: Boolean) {
                binding.ivRedo.isEnabled = canRedo
            }

        })
    }



}