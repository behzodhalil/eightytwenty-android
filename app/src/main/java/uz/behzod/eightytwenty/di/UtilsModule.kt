package uz.behzod.eightytwenty.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import uz.behzod.eightytwenty.utils.providers.DispatcherProvider
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import uz.behzod.eightytwenty.utils.providers.StringProvider
import uz.behzod.eightytwenty.utils.providers.StringProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun providesStringProvider(@ApplicationContext context: Context): StringProvider {
        return StringProviderImpl(context)
    }

    @Provides
    @Singleton
    fun providesDispatcherProvider(): IDispatcherProvider {
        return DispatcherProvider()
    }

    @Provides
    @Singleton
    @CallbackScope
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
}