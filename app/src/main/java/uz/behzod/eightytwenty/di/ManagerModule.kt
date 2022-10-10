package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.data.manager.PermissionManager
import uz.behzod.eightytwenty.data.manager.PermissionManagerImpl
import uz.behzod.eightytwenty.utils.manager.ImageStorageManagerImpl
import uz.behzod.eightytwenty.utils.manager.ImageStoreManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    @Singleton
    fun providesPermissionManager(
        manager: PermissionManagerImpl
    ): PermissionManager

    @get: Binds
    @get: Singleton
    val ImageStorageManagerImpl.bindImageStoreManager: ImageStoreManager
}