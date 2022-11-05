package uz.behzod.eightytwenty.features.productivity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentProductivityBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class ProductivityFragment : Fragment(R.layout.fragment_productivity) {

    private val binding: FragmentProductivityBinding by viewBinding(FragmentProductivityBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val url =
            "https://images.unsplash.com/photo-1511367461989-f85a21fda167?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3731&q=80"
        binding.avatarView.load(url)
    }

}