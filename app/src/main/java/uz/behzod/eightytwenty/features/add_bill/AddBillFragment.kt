package uz.behzod.eightytwenty.features.add_bill

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.databinding.FragmentAddBillBinding
import uz.behzod.eightytwenty.utils.view.FormField
import uz.behzod.eightytwenty.utils.view.FormFieldText
import uz.behzoddev.ui_toast.successMessage


@AndroidEntryPoint
class AddBillFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddBillBinding? = null
    private val binding: FragmentAddBillBinding get() = _binding!!
    private val fieldName by lazy {
        FormFieldText(
            scope = lifecycleScope,
            textInputLayout = binding.tilAddBillName,
            textInputText = binding.tieAddBillName,
            validation = { value ->
                when {
                    value.isNullOrBlank() -> "Bill name is required"
                    else -> null
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAddBillBinding.inflate(inflater, container, false)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
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
                behavior.skipCollapsed = true
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
        renderName()
        //
    }

    private fun setupHeight(v: View) {
        val layoutParams = v.layoutParams
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        v.layoutParams = layoutParams
    }

    private fun renderName() = lifecycleScope.launch() {
        fieldName.disable()
        if (fieldName.validate()) {
            successMessage("Name is saved")
        }
        fieldName.enable()
    }


}