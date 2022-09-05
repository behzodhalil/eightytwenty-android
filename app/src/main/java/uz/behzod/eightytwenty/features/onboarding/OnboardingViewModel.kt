package uz.behzod.eightytwenty.features.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.utils.providers.StringProvider
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val stringProvider: StringProvider
) : ViewModel() {

    private var _viewEffect = MutableSharedFlow<OnboardingViewEffect>()

    val viewEffect: SharedFlow<OnboardingViewEffect> get() = _viewEffect
    val lists : MutableList<OnboardingItem> = ArrayList()

    init {
        val items = listOf(
            OnboardingItem(
                title = stringProvider.getString(R.string.text_task_title),
                description = stringProvider.getString(R.string.text_task_description),
                image = R.drawable.bg_task
            ),
            OnboardingItem(
                title = stringProvider.getString(R.string.text_note_title),
                description = stringProvider.getString(R.string.text_note_description),
                image = R.drawable.bg_note
            ),
            OnboardingItem(
                title = stringProvider.getString(R.string.text_habit_title),
                description = stringProvider.getString(R.string.text_habit_description),
                image = R.drawable.bg_habit
            ),
            OnboardingItem(
                title = stringProvider.getString(R.string.text_time_title),
                description = stringProvider.getString(R.string.text_time_description),
                image = R.drawable.bg_time
            )
        )

        lists.addAll(
            items
        )

    }
    fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.GetStartedEvent -> {
                setOnboardingViewEffects(OnboardingViewEffect.GetStartedViewEffect)
            }
            OnboardingEvent.NextEvent -> {
                setOnboardingViewEffects(OnboardingViewEffect.NextViewEffect)

            }
            OnboardingEvent.SkipEvent -> {
                setOnboardingViewEffects(OnboardingViewEffect.SkipViewEffect)
            }
        }
    }

    private fun setOnboardingViewEffects(effect: OnboardingViewEffect) {
        viewModelScope.launch {
            when (effect) {
                OnboardingViewEffect.GetStartedViewEffect -> {
                    _viewEffect.emit(OnboardingViewEffect.GetStartedViewEffect)
                }
                OnboardingViewEffect.NextViewEffect -> {
                    _viewEffect.emit(OnboardingViewEffect.NextViewEffect)
                }
                OnboardingViewEffect.SkipViewEffect -> {
                    _viewEffect.emit(OnboardingViewEffect.SkipViewEffect)
                }
            }
        }

    }


}