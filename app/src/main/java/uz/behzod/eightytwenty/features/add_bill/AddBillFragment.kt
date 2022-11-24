package uz.behzod.eightytwenty.features.add_bill

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentAddBillBinding
import uz.behzod.eightytwenty.utils.extension.*
import uz.behzod.eightytwenty.utils.formatter.DateTimeFormatter
import uz.behzoddev.ui_toast.errorMessage
import uz.behzoddev.ui_toast.successMessage

@AndroidEntryPoint
class AddBillFragment : BottomSheetDialogFragment() {

    companion object {
        private const val SOFT_INPUT_RESIZE = 16
    }
    private var _binding: FragmentAddBillBinding? = null
    private val binding: FragmentAddBillBinding get() = _binding!!

    private val viewModel: AddBillViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddBillBinding.inflate(inflater, container, false)
        dialog?.window?.setSoftInputMode(SOFT_INPUT_RESIZE)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)

        dialog.setOnShowListener {
            val bottomSheet = it as BottomSheetDialog

            val parentLayout =
                bottomSheet.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

            parentLayout?.let { result ->
                BottomSheetBehavior.from(result).apply {
                    skipCollapsed = true
                    state = BottomSheetBehavior.STATE_EXPANDED
                }
                setupHeight(result)
            }
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
        saveBill()
    }

    private fun setupView() {
        initTypeAdapter()

        binding.tieAddBillDate.isEnabled = false

        binding.tieAddBillName.addTextChangedListener { viewModel.reduceName(it.asStringOrEmpty()) }
        binding.tieAddBillAmountDue.addTextChangedListener {
            viewModel.reduceAmount(it.asLongOrEmpty())
        }
        binding.actAddBillForm.addTextChangedListener { viewModel.reduceBiller(it.asStringOrEmpty()) }
        binding.tilAddBillDate.setEndIconOnClickListener {
            datePicker(action = { timestamp ->
                viewModel.reduceTimestamp(timestamp)
            }, finish = {
                viewModel.isDatePicked(true)
            })
        }
    }

    private fun setupHeight(v: View) {
        val layoutParams = v.layoutParams
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        v.layoutParams = layoutParams
    }

    private fun initTypeAdapter() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.view_holder_auto_complete,
            stringArray(R.array.bill_types)
        )

        binding.actAddBillForm.setAdapter(adapter)
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: AddBillState) {
        if (state.isSaved) {
            successMessage("Bill is saved")
        }
        if (state.isSaveFailed) {
            successMessage("Saving is failed")
        }

        if (state.isDatePicked) {
            val timestamp = viewModel.currentState.timestamp?.format(DateTimeFormatter.asTime(
                requireContext(),
                false))
            binding.tieAddBillDate.setText(timestamp)
            viewModel.isDatePicked(false)
        }

    }

    private fun saveBill() {
        val name = binding.tieAddBillName
        val amount = binding.tieAddBillAmountDue
        val date = binding.tieAddBillDate
        val type = binding.actAddBillForm

        binding.btnSaveBill.setOnClickListener {
            name.checkNotNull {
                errorMessage("Пожалуйста, введите имя")
            }
            amount.checkNotNull {
                errorMessage("Пожалуйста, введите сумму")
            }
            date.checkNotNull {
                errorMessage("Пожалуйста, введите дату")
            }
            type.checkNotNull {
                errorMessage("Пожалуйста, введите тип")
            }

            viewModel.insertBill()
        }
    }
}
