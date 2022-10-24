package uz_behzoddev.core_ui.view.edit_text

interface UndoRedoHistory {
    fun undo(
        before: String,
        isDeleted: Boolean,
        onCompleteListener: (
            text: String, endIndex: Int, isUndo: Boolean, isDeactivateUndo: Boolean
        ) -> Unit
    )

    fun add(param: UndoRedoItem)

    fun redo(
        before: String,
        isDeleted: Boolean,
        onCompleteListener: (
            text: String, endIndex: Int, isUndo: Boolean, isDeactivateUndo: Boolean
        ) -> Unit
    )

    fun clear()

    fun getPreviousPosition()

    fun getNextPosition()
}
