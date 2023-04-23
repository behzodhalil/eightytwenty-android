package io.behzod.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.behzod.domain.FormValidator
import io.behzod.domain.ValidationForm


@Module
@InstallIn(ViewModelComponent::class)
interface AuthDomainModule {

    @Binds
    @ViewModelScoped
    fun bindValidationForm(validateForm: FormValidator): ValidationForm
}
