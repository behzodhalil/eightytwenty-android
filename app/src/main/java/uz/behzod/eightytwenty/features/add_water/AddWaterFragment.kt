package uz.behzod.eightytwenty.features.add_water

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
import uz.behzod.eightytwenty.databinding.FragmentAddWaterBinding
import uz.behzod.eightytwenty.utils.constants.Water
import uz.behzod.eightytwenty.utils.extension.asStringOrEmpty
import uz.behzod.eightytwenty.utils.extension.stringArray
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
    }

    private fun setupView() {
        initFrequencyAdapter()

        binding.actAddWaterFrequency.addTextChangedListener { viewModel.reduceFrequency(it.asStringOrEmpty()) }

        binding.llHotCup.setOnClickListener {
            viewModel.reduceQuantity(Water.HOT_CUP.quantity)
            viewModel.reduceImage(Water.HOT_CUP.image)
        }
        binding.llMug.setOnClickListener {
            viewModel.reduceQuantity(Water.MUG.quantity)
            viewModel.reduceImage(Water.MUG.image)
        }
        binding.llMineralWater.setOnClickListener {
            viewModel.reduceQuantity(Water.MINERAL_WATER.quantity)
            viewModel.reduceImage(Water.MINERAL_WATER.image)
        }
        binding.llBottle.setOnClickListener {
            viewModel.reduceQuantity(Water.BOTTLE.quantity)
            viewModel.reduceImage(Water.BOTTLE.image)
        }
        binding.llGlass.setOnClickListener {
            viewModel.reduceQuantity(Water.GLASS.quantity)
            viewModel.reduceImage(Water.GLASS.image)
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
    }

    private fun saveWater() {

    }
}
