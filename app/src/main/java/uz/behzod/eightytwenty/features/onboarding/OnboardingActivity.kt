package uz.behzod.eightytwenty.features.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.databinding.ActivityOnboardingBinding
import uz.behzod.eightytwenty.utils.ext.Zero

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()

    private var position = Int.Zero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        observeEvents()

    }

    private fun observeEvents() =
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewEffect.collect { effect ->
                    observeEffects(effect)
                }
            }
        }

    private fun observeEffects(effect: OnboardingViewEffect) {
        when(effect) {
            OnboardingViewEffect.GetStartedViewEffect -> onNavigateToBecomeProductivy()
            OnboardingViewEffect.NextViewEffect -> onNavigateToNext()
            OnboardingViewEffect.SkipViewEffect -> onNavigateToSkip()
        }

    }

    private fun onNavigateToNext() {
        position = binding.viewPager.currentItem

        if (position < viewModel.lists.size) {
            position++
            binding.viewPager.currentItem = position
        }


    }

    private fun onNavigateToSkip() {

    }

    private fun onNavigateToBecomeProductivy() {

    }

    private fun onDisplayBecomeProductivity(isSeen: Boolean) {
        if (isSeen) {
            binding.btnNext.hide()
            binding.tvSkip.hide()
            binding.ivRight.hide()

            binding.btnBecomeProductivity.show()
        } else {
            binding.btnNext.show()
            binding.tvSkip.show()
            binding.ivRight.show()

            binding.btnBecomeProductivity.hide()
        }
    }

}