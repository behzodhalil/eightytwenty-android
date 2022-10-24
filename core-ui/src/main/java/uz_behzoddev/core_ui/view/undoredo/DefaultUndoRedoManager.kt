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
                if (before == originalText && !isDeleted) {
                    onCompletedEvent(before, before.length, false, true)
                }
            }
            Once -> TODO()
        }
    }

    override fun redo(
        type: UndoRedoType,
        after: String,
        isDeleted: Boolean,
        onCompletedEvent: (new: String, index: Int, isSuccessful: Boolean, isDeactivate: Boolean) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun add(param: UndoRedoItem) {
        TODO("Not yet implemented")
    }

    private fun checkCurrentState(text: String) {
        if (currentText.isBlank()) currentText = text
    }
}