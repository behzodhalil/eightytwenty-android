package uz.behzod.eightytwenty.features.reminder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentBillBinding
import uz.behzod.eightytwenty.features.add_bill.AddBillFragment
import uz.behzod.eightytwenty.utils.extension.transaction
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class BillFragment: Fragment(R.layout.fragment_bill) {

    private val binding: FragmentBillBinding by  viewBinding(FragmentBillBinding::bind)
    private lateinit var billAdapter: BillAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        billAdapter = BillAdapter()
        binding.rvBill.adapter = billAdapter

        binding.btnAddBill.setOnClickListener { route(BillRoute.AddBillRoute) }
    }

    private fun route(route: BillRoute) {
        when(route) {
            BillRoute.AddBillRoute -> {
                val fragment = AddBillFragment()
                transaction(fragment)
            }
        }
    }
}