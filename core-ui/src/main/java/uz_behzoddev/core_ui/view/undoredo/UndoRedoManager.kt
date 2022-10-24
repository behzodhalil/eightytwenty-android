package uz_behzoddev.core_ui.view.undoredo

import uz_behzoddev.core_ui.view.edit_text.UndoRedoItem

internal interface UndoRedoManager {

    fun undo(
        type: UndoRedoType,
        before: String,
        isDeleted: Boolean = false,
        onCompletedEvent: (
            new: String, index: Int, isSuccessful: Boolean, isDeactivate: Boolean
        ) -> Unit
    )

    fun redo(
        type: UndoRedoType,
        after: String,
        isDeleted: Boolean = false,
        onCompletedEvent: (
            new: String, index: Int, isSuccessful: Boolean, isDeactivate: Boolean
        ) -> Unit
    )

    fun clear()
    fun add(param: UndoRedoItem)
}