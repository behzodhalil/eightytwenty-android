package uz_behzoddev.core_ui.view.edit_text

class DefaultUndoRedoHistory : UndoRedoHistory {

    private var currentPosition: TextPosition? = null
    private var startPosition: TextPosition? = null
    private var endPosition: TextPosition? = null

    override fun undo(
        before: String,
        isDeleted: Boolean,
        onCompleteListener: (
            text: String, endIndex: Int, isUndo: Boolean, isDeactivateUndo: Boolean
        )
        -> Unit
    ) {
        if (currentPosition == null) {
            onCompleteListener(
                before,
                before.length,
                false,
                true
            )
            return
        }

        val undoRedoItem = currentPosition!!.text
        val startIndex = undoRedoItem.range.first
        val endIndex = undoRedoItem.range.last
        val range = undoRedoItem.range

        try {
            val original = before.replaceRange(
                startIndex,
                endIndex,
                replacement = ""
            )
            val isDeactivate: Boolean = if (isDeleted) {
                true
            } else {
                currentPosition = currentPosition!!.previousPosition
                currentPosition == null
            }

            onCompleteListener(
                original,
                range.first,
                true,
                isDeactivate
            )
        } catch (e: Exception) {
            removeCurrentPosition()
            onCompleteListener(
                before,
                before.length,
                false,
                true
            )
        }
    }

    override fun add(param: UndoRedoItem) {
        val position = TextPosition(param)

        if (currentPosition == null) {
            currentPosition = position
            startPosition = position
            endPosition = position
        } else {
            currentPosition
                ?.let { it.afterPosition = position }
            position.previousPosition = currentPosition
            currentPosition = position
            endPosition = position
        }
    }

    override fun redo(
        before: String,
        isDeleted: Boolean,
        onCompleteListener: (text: String, endIndex: Int, isUndo: Boolean, isDeactivateUndo: Boolean) -> Unit
    ) {
        currentPosition = if (isDeleted) {
            currentPosition
        } else {
            currentPosition?.afterPosition ?: startPosition
        }

        if (currentPosition == null) {
            onCompleteListener(
                before,
                before.length,
                false,
                true
            )

            return
        }

        val undoRedoItem = currentPosition!!.text
        val startIndex = undoRedoItem.range.first
        val text = undoRedoItem.text

        try {
            val original = if (before.isEmpty())  {
                text
            } else {
                before.replaceRange(startIndex,startIndex,text)
            }
        } catch (e: Exception) {
            removeCurrentPosition()
            onCompleteListener(
                before,
                before.length,
                false,
                true
            )
        }
    }

    override fun clear() {
        currentPosition = null
        startPosition = null
        endPosition = null
    }

    override fun getPreviousPosition() {
        currentPosition = startPosition?.previousPosition
    }

    override fun getNextPosition() {
        currentPosition = endPosition
    }

    private fun removeCurrentPosition() {
        if (currentPosition == null) return

        currentPosition = currentPosition!!.previousPosition
        currentPosition?.afterPosition = null

        endPosition = currentPosition
    }

}