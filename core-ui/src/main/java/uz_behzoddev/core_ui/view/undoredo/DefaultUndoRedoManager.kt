package uz_behzoddev.core_ui.view.undoredo

import uz_behzoddev.core_ui.view.edit_text.UndoRedoHistory
import uz_behzoddev.core_ui.view.edit_text.UndoRedoItem
import javax.inject.Inject

class DefaultUndoRedoManager @Inject constructor(
    private val undoRedoHistory: UndoRedoHistory
) : UndoRedoManager {

    private var currentText = ""
    private var originalText = ""
    private var isCompletedDeletion: Boolean = false

    override fun undo(
        type: UndoRedoType,
        before: String,
        isDeleted: Boolean,
        onCompletedEvent: (new: String, index: Int, isSuccessful: Boolean, isDeactivate: Boolean) -> Unit
    ) {
        checkCurrentState(before)

        if (isCompletedDeletion) {
            isCompletedDeletion = false
            redo(
                type,
                before,
                true
            ) { new, index, isSuccessful, isDeactivate ->
                isCompletedDeletion = true
                onCompletedEvent(new, index, isSuccessful, isDeactivate)
            }
            return
        }

        when (type) {
            All -> {
                undoRedoHistory.getPreviousPosition()
                onCompletedEvent(originalText, originalText.length, true, true)
            }
            Once -> {
                if (before == originalText && !isDeleted) {
                    onCompletedEvent(before, before.length,false, true)
                } else {
                    undoRedoHistory.redo(
                        before,
                        isDeleted,
                    ) { text, endIndex, isUndo, isDeactivateUndo ->
                        onCompletedEvent(text, endIndex, isUndo, isDeactivateUndo)
                    }
                }
            }
        }
    }

    override fun redo(
        type: UndoRedoType,
        after: String,
        isDeleted: Boolean,
        onCompletedEvent: (new: String, index: Int, isSuccessful: Boolean, isDeactivate: Boolean) -> Unit
    ) {
        if (isCompletedDeletion) {
            isCompletedDeletion = false
            undo(type, after, true) { newText, index, isSuccessful, shouldDeactivate ->
                isCompletedDeletion = true
                onCompletedEvent(newText, index, isSuccessful, shouldDeactivate)
            }
            return
        }

        when (type) {
            Once -> {
                if (after == currentText && !isDeleted) {
                    onCompletedEvent(after, after.length, false, true)
                } else {
                    undoRedoHistory.redo(
                        after,
                        isDeleted
                    ) { text, endIndex, isSuccessful, shouldDeactivate ->
                        onCompletedEvent(text, endIndex, isSuccessful, shouldDeactivate)
                    }
                }
            }
            All -> {
                undoRedoHistory.getNextPosition()
                onCompletedEvent(currentText, currentText.length, true, true)
            }
        }
    }

    override fun clear() {
        undoRedoHistory.clear()
    }

    override fun add(param: UndoRedoItem) {
        undoRedoHistory.add(param = param)
    }

    private fun checkCurrentState(text: String) {
        if (currentText.isBlank()) currentText = text
    }
}