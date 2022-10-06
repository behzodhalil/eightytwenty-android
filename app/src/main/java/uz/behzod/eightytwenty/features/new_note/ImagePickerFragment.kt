package uz.behzod.eightytwenty.features.new_note

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.databinding.FragmentImagePickerBinding

@AndroidEntryPoint
class ImagePickerFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentImagePickerBinding? = null
    private val binding: FragmentImagePickerBinding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheet = it as BottomSheetDialog

            val parentLayout =
                bottomSheet.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { result ->
                val behavior = BottomSheetBehavior.from(result)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImagePickerBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView() {
        binding.tvPickFromGallery.setOnClickListener {
            onPickFromGalleryListener()
            dismiss()
        }
        binding.tvTakePicture.setOnClickListener {
            onTakePictureListener()
            dismiss()
        }
    }

    fun setPickFromGalleryListener(listener: () -> Unit) {
        onPickFromGalleryListener = listener

    }

    fun setTakePictureListener(listener: () -> Unit) {
        onTakePictureListener = listener
    }

    private var onPickFromGalleryListener: () -> Unit = {}
    private var onTakePictureListener: () -> Unit = {}

}