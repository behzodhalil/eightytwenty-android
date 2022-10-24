package uz_behzoddev.core_ui.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz_behzoddev.core_ui.view.edit_text.DefaultUndoRedoHistory
import uz_behzoddev.core_ui.view.edit_text.UndoRedoHistory
import uz_behzoddev.core_ui.view.undoredo.DefaultUndoRedoManager
import uz_behzoddev.core_ui.view.undoredo.UndoRedoManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @get: Binds
    @get: Singleton
    val DefaultUndoRedoHistory.bindUndoRedoHistory: UndoRedoHistory

    @get: Binds
    @get: Singleton
    val DefaultUndoRedoManager.bindUndoRedoManager: UndoRedoManager
}