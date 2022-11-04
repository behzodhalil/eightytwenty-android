package uz.behzod.eightytwenty.features.sign_in

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentSignInBinding
import uz.behzod.eightytwenty.utils.extension.asStringOrEmpty
import uz.behzod.eightytwenty.utils.extension.navigateTo
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.view.viewBinding
import uz.behzoddev.ui_toast.errorMessage
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)
    private val viewModel: SignInViewModel by viewModels()

    @Inject
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (auth.currentUser != null && auth.currentUser?.isAnonymous == false) {
            val route = SignInFragmentDirections.actionSignInFragmentToNoteFragment()
            navigateTo(route)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeState()
        navigateToSignUp()
    }

    private fun setupView() {
        binding.etEmail.addTextChangedListener { viewModel.updateEmail(it.asStringOrEmpty()) }
        binding.etPassword.addTextChangedListener { viewModel.updatePassword(it.asStringOrEmpty()) }
        binding.btnNext.setOnClickListener {
            var count = 0
            printDebug { "Clicked: ${count++}" }
            viewModel.signIn() }
    }

    private fun observeState() {
        viewModel.state
            .flowWithLifecycle(lifecycle)
            .onEach { state -> renderState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun renderState(state: SignInState) {
        if (state.isSuccess) {
            val route = SignInFragmentDirections.actionSignInFragmentToNoteFragment()
            navigateTo(route)
        }

        if (state.isFailed) {
            errorMessage(
                "Эта учетная запись не существует. " +
                        "Проверьте еще раз, пожалуйста."
            )

        }
    }

    private fun signIn() {
        binding.btnNext.setOnClickListener {
            viewModel.signIn()
        }
    }

    private fun navigateToSignUp() {
        binding.tvSignUp.setOnClickListener {
            val route = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            navigateTo(route)
        }
    }
}