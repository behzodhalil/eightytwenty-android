package uz.behzod.eightytwenty.features.add_bill

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentAddBillBinding
import uz.behzod.eightytwenty.utils.extension.focusAndShowKeyboard
import uz.behzod.eightytwenty.utils.extension.showKeyboard
import uz.behzod.eightytwenty.utils.view.SoftKeyboardListener


@AndroidEntryPoint
class AddBillFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddBillBinding? = null
    private val binding: FragmentAddBillBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAddBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheet = it as BottomSheetDialog

            val parentLayout =
                bottomSheet.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { result ->
                val behavior = BottomSheetBehavior.from(result)
                setupHeight(result)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {

        //
    }

    private fun setupHeight(v: View) {
        val layoutParams = v.layoutParams
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        v.layoutParams = layoutParams
    }


}