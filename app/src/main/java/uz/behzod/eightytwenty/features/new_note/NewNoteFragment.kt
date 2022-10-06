package uz.behzod.eightytwenty.features.new_note

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
import uz.behzod.eightytwenty.databinding.FragmentNewNoteBinding
import uz.behzod.eightytwenty.utils.extension.*
import uz.behzod.eightytwenty.utils.helper.BitmapHelper
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.ZonedDateTime

@AndroidEntryPoint
class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private val binding by viewBinding(FragmentNewNoteBinding::bind)
    private val viewModel: NewNoteWithReduxViewModel by viewModels()
    private val args: NewNoteFragmentArgs by navArgs()

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { state -> onTakePictureListener(state)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupUi()
        observerState()
    }

    private fun setupUI() {
        initTimestamp()
    }

    private fun initTimestamp() {
        binding.tvDate.text = ZonedDateTime.now().toString()
    }

    private fun setupUi() {
        binding.etTitle.addTextChangedListener { viewModel.modifyTitle(it.asStringOrEmpty()) }
        binding.etDesc.addTextChangedListener { viewModel.modifyDesc(it.asStringOrEmpty()) }
        binding.tvDate.addTextChangedListener { viewModel.modifyTimestamp(it.asStringOrEmpty().asZoneDateTime()) }

        viewModel.modifyIsTrashed(false)

        viewModel.modifyGroupUid(args.categoryId)

        binding.btnSaveOrCancel.setOnClickListener {
            viewModel.insertNote()
        }

        binding.ivImage.setOnClickListener {
            val screen = ImagePickerFragment().apply {
                setTakePictureListener {
                    onTakePicture()
                }
            }
            transaction(screen)
        }
    }

    private fun observerState() {
        viewModel.state
            .flowWithLifecycle(viewLifecycleOwner.lifecycle,Lifecycle.State.STARTED)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: NewNoteViewState) {
        if (state.isSuccess) {
            if(navController.currentDestination?.id == R.id.newNoteFragment) {
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

    private fun onPickFromGallery() {}
    private fun onPickFromGalleryListener() {}
    private fun onTakePictureListener(state: Boolean) {
        if (state) {
            showMessage("Photo was taken")
        } else {
            showMessage("No photo was taken")
        }
    }

}