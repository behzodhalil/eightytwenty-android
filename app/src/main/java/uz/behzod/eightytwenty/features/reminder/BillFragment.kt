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
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.databinding.FragmentBillBinding
import uz.behzod.eightytwenty.features.add_bill.AddBillFragment
import uz.behzod.eightytwenty.utils.extension.gone
import uz.behzod.eightytwenty.utils.extension.show
import uz.behzod.eightytwenty.utils.extension.transaction
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class BillFragment : Fragment(R.layout.fragment_bill) {

    private val binding: FragmentBillBinding by viewBinding(FragmentBillBinding::bind)

    private lateinit var billAdapter: BillAdapter

    private val viewModel: BillViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
    }

    private fun setupView() {
        billAdapter = BillAdapter()
        binding.rvBill.adapter = billAdapter

        binding.btnAddBill.setOnClickListener { route(BillRoute.AddBillRoute) }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: BillState) {
        if (state.isSuccess) {


            getBills(state.bills)
        }
        if (state.isEmpty) {

        }
    }

    private fun getBills(bills: List<BillEntity>) {
        return billAdapter.submitList(bills)
    }

    private fun route(route: BillRoute) {
        when (route) {
            BillRoute.AddBillRoute -> {
                val fragment = AddBillFragment()
                transaction(fragment)
            }
        }
    }
}
