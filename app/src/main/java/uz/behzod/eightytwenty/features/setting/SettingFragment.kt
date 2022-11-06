package uz.behzod.eightytwenty.features.setting

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
import uz.behzod.eightytwenty.databinding.FragmentSettingBinding

@AndroidEntryPoint
class SettingFragment : BottomSheetDialogFragment() {

    companion object {
        fun instance(): SettingFragment {
            return SettingFragment()
        }
    }

    private var _binding: FragmentSettingBinding? = null
    private val binding: FragmentSettingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSettingBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheet = it as BottomSheetDialog

            val parentLayout =
                bottomSheet.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { layout ->
                val behavior = BottomSheetBehavior.from(layout)
                height(view = layout)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun height(view: View) {
        val layoutParams = view.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        view.layoutParams = layoutParams
    }

    private fun route(direction: SettingRoute) {

    }
}