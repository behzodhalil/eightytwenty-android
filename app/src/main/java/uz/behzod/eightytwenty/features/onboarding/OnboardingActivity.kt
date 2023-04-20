package uz.behzod.eightytwenty.features.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.behzod.onboarding.Onboarding
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.databinding.ActivityOnboardingBinding
import uz.behzod.eightytwenty.features.main.MainActivity
import uz.behzod.eightytwenty.utils.extension.*

@AndroidEntryPoint
class OnboardingActivity : ComponentActivity() {

    companion object {
        private const val PREFS_ONBOARDING = "PREFS_ONBOARDING"
        private const val PREFS_IS_OPENED = "PREFS_ONBOARDING_IS_OPENED"
    }


    /*private lateinit var binding: ActivityOnboardingBinding
    private lateinit var mViewPager: OnboardingViewPagerAdapter*/
    private val viewModel: OnboardingViewModel by viewModels()

    private var position = Int.Zero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Onboarding {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        /*binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setUpFullContent()
        if (isOnboardingOpened()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)
        setupView()*/
    }
/*
    private fun setUpFullContent() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN
        )

        supportActionBar?.hide()
    }*/

  /*  private fun setupView() {
        initViewPager()

        observeEvents()
    }*/

  /*  private fun initViewPager() {
        mViewPager = OnboardingViewPagerAdapter(this, viewModel.lists)
        binding.viewPager.adapter = mViewPager

        binding.btnNext.setOnClickListener { viewModel.onEvent(OnboardingEvent.NextEvent) }
        binding.tvSkip.setOnClickListener { viewModel.onEvent(OnboardingEvent.SkipEvent) }
        binding.btnBecomeProductivity.setOnClickListener { viewModel.onEvent(OnboardingEvent.GetStartedEvent) }
    }

    private fun observeEvents() {
        viewModel.viewEffect.flowWithLifecycle(lifecycle)
            .onEach { observeEffects(it) }
            .launchIn(this.lifecycleScope)
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

        if (position == viewModel.lists.size - 1) {
            onDisplayBecomeProductivity()
        }
    }

    private fun onNavigateToSkip() {
        binding.viewPager.currentItem = viewModel.lists.size - 1
        onDisplayBecomeProductivity()
    }

    private fun onNavigateToBecomeProductivity() {
        startAndFinishActivity<MainActivity>()
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
        return pref.getBoolean(PREFS_IS_OPENED, false)
    }

    private fun setOnboardingOpened() {
        val pref = applicationContext.getSharedPreferences(
            PREFS_ONBOARDING,
            Context.MODE_PRIVATE
        )
        val editor = pref.edit().putBoolean(PREFS_IS_OPENED, true)
        editor.apply()
    }*/

}
