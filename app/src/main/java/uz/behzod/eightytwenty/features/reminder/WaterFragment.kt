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
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity
import uz.behzod.eightytwenty.databinding.FragmentWaterBinding
import uz.behzod.eightytwenty.features.add_water.AddWaterFragment
import uz.behzod.eightytwenty.utils.extension.transaction
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class WaterFragment : Fragment(R.layout.fragment_water) {

    private val binding: FragmentWaterBinding by viewBinding(FragmentWaterBinding::bind)

    private lateinit var adapter: WaterAdapter

    private val viewModel: WaterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
    }

    private fun setupView() {
        adapter = WaterAdapter()

        binding.rvWater.adapter = adapter

        binding.btnAddWater.setOnClickListener { route(WaterRoute.AddWater) }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: WaterState) {
        if (state.isSuccess) {
            getWaters(state.waters)
        }
    }

    private fun getWaters(waters: List<WaterEntity>) {
        adapter.submitList(waters)
    }

    private fun route(route: WaterRoute) {
        when (route) {
            WaterRoute.AddWater -> {
                val dialog = AddWaterFragment()
                transaction(dialog)
            }
        }
    }
}
