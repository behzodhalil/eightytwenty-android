package io.behzod.onboarding

data class OnboardingItem(
    val title: Int,
    val description: Int,
    val image: Int,
) {
    companion object {
        fun items(): List<OnboardingItem> {
            return listOf(
                OnboardingItem(
                    title = io.behzod.features.R.string.create_task,
                    description = io.behzod.features.R.string.create_task_description,
                    image = io.behzod.features.R.drawable.bg_task
                ),
                OnboardingItem(
                    title = io.behzod.features.R.string.write_note,
                    description = io.behzod.features.R.string.write_note_description,
                    image = io.behzod.features.R.drawable.bg_note
                ),
                OnboardingItem(
                    title = io.behzod.features.R.string.form_habits,
                    description = io.behzod.features.R.string.form_habit_description,
                    image = io.behzod.features.R.drawable.bg_task
                ),
                OnboardingItem(
                    title = io.behzod.features.R.string.everything_on_the_shelves,
                    description = io.behzod.features.R.string.everything_on_the_shelves_description,
                    image = io.behzod.features.R.drawable.bg_time
                )
            )
        }
    }
}
