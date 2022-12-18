package uz.behzod.eightytwenty.features.add_pill

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentAddPillBinding
import uz.behzod.eightytwenty.utils.extension.*
import uz.behzod.eightytwenty.utils.view.viewBinding
import uz.behzoddev.ui_toast.errorMessage
import uz.behzoddev.ui_toast.successMessage

@AndroidEntryPoint
class AddPillFragment : Fragment(R.layout.fragment_add_pill) {

    private val binding: FragmentAddPillBinding by viewBinding(FragmentAddPillBinding::bind)
    private val viewModel: AddPillViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
        savePill()
    }


    private fun setupView() {
        renderForm()
        renderUnit()
        renderFrequency()

        binding.etAddPillDose.addTextChangedListener { viewModel.reduceDose(it.asLongOrEmpty()) }
        binding.actAddPillForm.addTextChangedListener { viewModel.reduceForm(it.asStringOrEmpty()) }
        binding.actAddPillFrequency.addTextChangedListener { viewModel.reduceFrequency(it.asStringOrEmpty()) }
        binding.etAddPillName.addTextChangedListener { viewModel.reduceName(it.asStringOrEmpty()) }
        binding.etAddPillDuration.addTextChangedListener { viewModel.reduceDuration(it.asStringOrEmpty()) }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: AddPillState) {
        if (state.isSaved) {
            successMessage("Лекарства успешно сохранена")
            route(RouteAP.Pill)
        }
        if (state.isSaveFailed) {
            errorMessage("Не удалось сохранить лекарство")
        }
    }

    private fun renderForm() {
        val form = ArrayAdapter(requireContext(),
            R.layout.view_holder_auto_complete,
            stringArray(R.array.pill_type))
        binding.actAddPillForm.setAdapter(form)
    }

    private fun renderUnit() {
        val unit = ArrayAdapter(
            requireContext(),
            R.layout.view_holder_auto_complete,
            stringArray(R.array.pill_units))

        binding.actAddPillDose.setAdapter(unit)
    }

    private fun renderFrequency() {
        val frequency = ArrayAdapter(
            requireContext(),
            R.layout.view_holder_auto_complete,
            stringArray(R.array.pill_frequency)
        )
        binding.actAddPillFrequency.setAdapter(frequency)
    }

    private fun savePill() {
        binding.btnSavePill.setOnClickListener {
            val name = binding.etAddPillName
            val form = binding.actAddPillForm
            val dose = binding.etAddPillDose
            val time = binding.etAddPillTime
            val frequency = binding.actAddPillFrequency
            val duration = binding.etAddPillDuration

            if (name.checkNotNull { errorMessage("Пожалуйста, введите имя") }
                && form.checkNotNull { errorMessage("Пожалуйста, введите форму") }
                && dose.checkNotNull { errorMessage("Пожалуйста, введите дозу") }
                && time.checkNotNull { errorMessage("Пожалуйста, введите время") }
                && frequency.checkNotNull { errorMessage("Пожалуйста, введите частоту") }
                && duration.checkNotNull { errorMessage("Пожалуйста, введите продолжительность") }) {
                viewModel.insertPill()
            }
        }
    }

    private fun route(route: RouteAP) {
        when(route) {
            RouteAP.Pill -> {
                val direction = AddPillFragmentDirections.actionAddPillFragmentToReminderFragment()
                navigateTo(direction)
            }
        }
    }
}
