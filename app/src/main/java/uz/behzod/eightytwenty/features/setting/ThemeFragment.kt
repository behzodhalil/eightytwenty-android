package uz.behzod.eightytwenty.features.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.databinding.FragmentThemeBinding
import uz.behzod.eightytwenty.utils.extension.setWidthPercent

@AndroidEntryPoint
class ThemeFragment : DialogFragment() {

    companion object {
        fun instance(): ThemeFragment {
            return ThemeFragment()
        }
    }

    private var _binding: FragmentThemeBinding? = null
    private val binding: FragmentThemeBinding get() = _binding!!

    private val viewModel: ThemeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentThemeBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setWidthPercent(80)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupView() {
        binding.tvDark.setOnClickListener {
            viewModel.hasLight(false)
            viewModel.hasDark(true)
            viewModel.hasDone(true)
        }

        binding.tvLight.setOnClickListener {
            viewModel.hasLight(true)
            viewModel.hasDark(false)
        }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: ThemeState) {
        if (state.isLight) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }

        if (state.isDark) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        }

        if (state.isDone) {
            requireActivity().finish()
            // findNavController().navigateUp()
        }
    }

}
