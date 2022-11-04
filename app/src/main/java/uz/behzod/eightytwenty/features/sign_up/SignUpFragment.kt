package uz.behzod.eightytwenty.features.sign_up

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentSignUpBinding
import uz.behzod.eightytwenty.utils.extension.asStringOrEmpty
import uz.behzod.eightytwenty.utils.extension.navigateTo
import uz.behzod.eightytwenty.utils.view.viewBinding
import uz.behzoddev.ui_toast.errorMessage

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding: FragmentSignUpBinding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
        insertUser()
    }

    private fun setupView() {
        binding.etEmail.addTextChangedListener { viewModel.updateEmail(it.asStringOrEmpty()) }
        binding.etName.addTextChangedListener { viewModel.updateName(it.asStringOrEmpty()) }
        binding.etPassword.addTextChangedListener { viewModel.updatePassword(it.asStringOrEmpty()) }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { renderState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: SignUpState) {
        if (state.isSuccess) {
            viewModel.insertUser()
        }

        if (state.isCreated) {
            val route = SignUpFragmentDirections.actionSignUpFragmentToNoteFragment()
            navigateTo(route)
        }
    }

    private fun insertUser() {
        binding.btnNext.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty() && binding.etName.text.isNotEmpty()) {
                viewModel.createUser()
            } else {
                errorMessage("Пожалуйста, введите повторно!")
            }

        }
    }
}