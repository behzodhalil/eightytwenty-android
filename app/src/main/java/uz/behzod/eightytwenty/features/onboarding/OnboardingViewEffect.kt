package uz.behzod.eightytwenty.features.onboarding

sealed interface OnboardingViewEffect {
    object GetStartedViewEffect: OnboardingViewEffect
    object SkipViewEffect: OnboardingViewEffect
    object NextViewEffect: OnboardingViewEffect
}