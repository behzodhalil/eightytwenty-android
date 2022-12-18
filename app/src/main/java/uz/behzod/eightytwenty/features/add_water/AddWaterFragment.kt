package uz.behzod.eightytwenty.features.add_water

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentAddWaterBinding
import uz.behzod.eightytwenty.utils.constants.Water
import uz.behzod.eightytwenty.utils.extension.asStringOrEmpty
import uz.behzod.eightytwenty.utils.extension.datePicker
import uz.behzod.eightytwenty.utils.extension.stringArray
import uz.behzod.eightytwenty.utils.formatter.DateTimeFormatter
import uz.behzoddev.ui_toast.errorMessage
import uz.behzoddev.ui_toast.successMessage

@AndroidEntryPoint
class AddWaterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddWaterBinding? = null
    private val binding: FragmentAddWaterBinding get() = _binding!!

    private val viewModel: AddWaterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAddWaterBinding.inflate(inflater, container, false)

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
        saveWater()
    }

    private fun setupView() {
        initFrequencyAdapter()

        binding.apply {

            tieReminderTimeWater.isEnabled = false

            actAddWaterFrequency.addTextChangedListener { viewModel.reduceFrequency(it.asStringOrEmpty()) }

            llHotCup.setOnClickListener {
                llHotCup.alpha = 1F
                llMug.alpha = 0.3F
                llMineralWater.alpha = 0.3F
                llBottle.alpha = 0.3F
                llGlass.alpha = 0.3F

                viewModel.reduceQuantity(Water.HOT_CUP.quantity)
            }

            llMug.setOnClickListener {
                llMug.alpha = 1F

                llHotCup.alpha = 0.3F
                llMineralWater.alpha = 0.3F
                llBottle.alpha = 0.3F
                llGlass.alpha = 0.3F
                viewModel.reduceQuantity(Water.MUG.quantity)
            }

            llMineralWater.setOnClickListener {
                llMineralWater.alpha = 1F

                llMug.alpha = 0.3F
                llHotCup.alpha = 0.3F
                llBottle.alpha = 0.3F
                llGlass.alpha = 0.3F

                viewModel.reduceQuantity(Water.MINERAL_WATER.quantity)
            }

            llBottle.setOnClickListener {
                llBottle.alpha = 1F
                llMug.alpha = 0.3F
                llMineralWater.alpha = 0.3F
                llHotCup.alpha = 0.3F
                llGlass.alpha = 0.3F
                viewModel.reduceQuantity(Water.BOTTLE.quantity)
            }

            llGlass.setOnClickListener {
                llGlass.alpha = 1F

                llMug.alpha = 0.3F
                llMineralWater.alpha = 0.3F
                llBottle.alpha = 0.3F
                llHotCup.alpha = 0.3F
                viewModel.reduceQuantity(Water.GLASS.quantity)
            }

            tilReminderTimeWater.setEndIconOnClickListener {
                datePicker(action = {
                    viewModel.reduceReminderTime(it)
                }, finish = {
                    viewModel.isDatePicked(true)
                })
            }
        }


    }


    private fun setupHeight(v: View) {
        val layoutParams = v.layoutParams
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        v.layoutParams = layoutParams
    }

    private fun initFrequencyAdapter() {
        val frequency = ArrayAdapter(
            requireContext(),
            R.layout.view_holder_auto_complete,
            stringArray(R.array.pill_frequency)
        )

        binding.actAddWaterFrequency.setAdapter(frequency)
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: AddWaterState) {
        if (state.isSaved) {
            successMessage("Напоминание о воде успешно сохранено")
        }
        if (state.isSaveFailed) {
            errorMessage("Не удалось сохранить напоминание о воде")
        }

        if (state.isDatePicked) {
            val reminderTime = state.reminderTime?.format(
                DateTimeFormatter.asTime(requireContext(), false)
            )
            binding.tieReminderTimeWater.setText(reminderTime)
        }
    }

    private fun saveWater() {
        binding.btnSaveWater.setOnClickListener {
            viewModel.insertWater()
        }
    }

}
