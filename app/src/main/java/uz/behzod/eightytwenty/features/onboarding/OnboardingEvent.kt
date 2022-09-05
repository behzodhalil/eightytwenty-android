package uz.behzod.eightytwenty.features.onboarding

sealed interface OnboardingEvent {
    object GetStartedEvent: OnboardingEvent
    object SkipEvent: OnboardingEvent
    object NextEvent: OnboardingEvent
}