package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.utils.providers.DispatcherProvider
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import uz.behzod.eightytwenty.utils.providers.StringProvider
import uz.behzod.eightytwenty.utils.providers.StringProviderImpl

@Module
@InstallIn(SingletonComponent::class)
interface UtilsModule {

    @Binds
    fun providesStringProvider(
        provider: StringProviderImpl
    ): StringProvider

    @Binds
    fun providesDispatcherProvider(
        provider: DispatcherProvider
    ): IDispatcherProvider
}