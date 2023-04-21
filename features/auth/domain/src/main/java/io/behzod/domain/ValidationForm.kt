package io.behzod.domain


interface ValidationForm {
    operator fun invoke(email: String?, password: String?, name: String?): Boolean
}
