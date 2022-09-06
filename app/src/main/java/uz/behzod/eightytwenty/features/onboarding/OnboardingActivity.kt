package uz.behzod.eightytwenty.features.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.databinding.ActivityOnboardingBinding
import uz.behzod.eightytwenty.features.main.MainActivity
import uz.behzod.eightytwenty.utils.ext.Zero
import uz.behzod.eightytwenty.utils.ext.hide
import uz.behzod.eightytwenty.utils.ext.show

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var mViewPager: OnboardingViewPagerAdapter
    private val viewModel: OnboardingViewModel by viewModels()

    private var position = Int.Zero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setUpFullContent()
        setContentView(binding.root)
        setUpUI()
    }

    private fun setUpFullContent() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN
        )

        supportActionBar?.hide()
    }

    private fun setUpUI() {
        initViewPager()

        observeEvents()
    }

    private fun initViewPager() {
        mViewPager = OnboardingViewPagerAdapter(this, viewModel.lists)
        binding.viewPager.adapter = mViewPager

        binding.btnNext.setOnClickListener { viewModel.onEvent(OnboardingEvent.NextEvent) }
        binding.tvSkip.setOnClickListener { viewModel.onEvent(OnboardingEvent.SkipEvent) }
        binding.btnBecomeProductivity.setOnClickListener { viewModel.onEvent(OnboardingEvent.GetStartedEvent) }
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
        when (effect) {
            OnboardingViewEffect.GetStartedViewEffect -> onNavigateToBecomeProductivity()
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
        binding.viewPager.currentItem = viewModel.lists.size - 1
        onDisplayBecomeProductivity()
    }

    private fun onNavigateToBecomeProductivity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun onDisplayBecomeProductivity() {
        binding.btnNext.hide()
        binding.tvSkip.hide()
        binding.ivRight.hide()

        binding.btnBecomeProductivity.show()
    }

}