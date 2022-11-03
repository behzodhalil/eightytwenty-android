package uz.behzod.eightytwenty.features.onboarding

import android.content.Context
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
import uz.behzod.eightytwenty.utils.extension.Zero
import uz.behzod.eightytwenty.utils.extension.hide
import uz.behzod.eightytwenty.utils.extension.show

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    companion object {
        private const val PREFS_ONBOARDING = "PREFS_ONBOARDING"
        private const val PREFS_IS_OPENED = "PREFS_ONBOARDING_IS_OPENED"
    }

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var mViewPager: OnboardingViewPagerAdapter
    private val viewModel: OnboardingViewModel by viewModels()

    private var position = Int.Zero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setUpFullContent()
        if (isOnboardingOpened()) {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)
        setupView()
    }

    private fun setUpFullContent() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN
        )

        supportActionBar?.hide()
    }

    private fun setupView() {
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
        finish()
        setOnboardingOpened()
    }

    private fun onDisplayBecomeProductivity() {
        binding.btnNext.hide()
        binding.tvSkip.hide()
        binding.ivRight.hide()

        binding.btnBecomeProductivity.show()
    }

    private fun isOnboardingOpened(): Boolean {
        val pref = applicationContext.getSharedPreferences(
            PREFS_ONBOARDING,
            Context.MODE_PRIVATE
        )
        return pref.getBoolean(PREFS_IS_OPENED,false)
    }

    private fun setOnboardingOpened() {
        val pref = applicationContext.getSharedPreferences(
            PREFS_ONBOARDING,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit().putBoolean(PREFS_IS_OPENED,true)
        editor.commit()
    }

}