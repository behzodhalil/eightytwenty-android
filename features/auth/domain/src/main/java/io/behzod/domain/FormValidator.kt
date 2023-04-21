package io.behzod.domain

class FormValidator : ValidationForm {

    override fun invoke(email: String?, password: String?, name: String?): Boolean {
        return email?.isNotEmpty() == true
            && password?.isNotEmpty() == true
            && name?.isNotEmpty() == true
    }
}
