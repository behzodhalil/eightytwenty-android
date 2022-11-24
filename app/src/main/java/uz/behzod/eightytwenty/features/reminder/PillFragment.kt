package uz.behzod.eightytwenty.features.reminder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentPillBinding
import uz.behzod.eightytwenty.utils.extension.navigateTo
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class PillFragment : Fragment(R.layout.fragment_pill) {

    private val binding: FragmentPillBinding by viewBinding(FragmentPillBinding::bind)

    private lateinit var pillAdapter: PillAdapter

    private val viewModel: PillViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
    }

    private fun setupView() {
        initAdapter()

        binding.btnAddPill.setOnClickListener { route(PillRoute.AddPill) }
    }

    private fun initAdapter() {
        pillAdapter = PillAdapter()

        binding.rvPill.adapter = pillAdapter
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: PillState) {
        if (state.isSuccess) {
            pillAdapter.submitList(state.pills)
        }
    }

    private fun route(route: PillRoute) {
        when (route) {
            PillRoute.AddPill -> {
                val direction = ReminderFragmentDirections.actionReminderFragmentToAddPillFragment()
                navigateTo(direction)
            }
        }
    }
}
