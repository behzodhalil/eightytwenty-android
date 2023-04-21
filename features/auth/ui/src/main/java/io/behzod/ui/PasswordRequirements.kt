package io.behzod.ui

import androidx.annotation.StringRes

enum class PasswordRequirements (
    @StringRes val label: Int
    ) {
    CAPITAL_LETTER(io.behzod.features.R.string.password_requirement_capital),
    NUMBER(io.behzod.features.R.string.password_requirement_digit),
    EIGHT_CHARACTERS(io.behzod.features.R.string.password_requirement_characters)
}
