package uz.behzod.eightytwenty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.behzod.eightytwenty.data.manager.PermissionManager
import uz.behzod.eightytwenty.data.manager.PermissionManagerImpl
import uz.behzod.eightytwenty.data.source.DefaultNoteSourceManager
import uz.behzod.eightytwenty.data.source.NoteSourceManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    @Singleton
    fun providesPermissionManager(
        manager: PermissionManagerImpl
    ): PermissionManager

    @get:Binds
    @get:Singleton
    val DefaultNoteSourceManager.bindNoteManager: NoteSourceManager
}
