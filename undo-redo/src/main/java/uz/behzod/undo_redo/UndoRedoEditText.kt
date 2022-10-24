package uz.behzod.undo_redo

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class UndoEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {
    companion object {
        const val HISTORY_INFINITE = -1
    }

    private val undoRedoManager: UndoRedoManager = UndoRedoManager()
    private val textChangeListener: TextChangeListener = TextChangeListener()
    private var undoStateListener: UndoStateListener? = null
    private var undoStatus: Boolean = false
    private var redoStatus: Boolean = false

    init {
        addTextChangedListener(textChangeListener)
    }

    fun setUndoStatusListener(listener: UndoStateListener) {
        undoStateListener = listener
    }

    fun canUndo(): Boolean {
        return undoRedoManager.canUndo()
    }

    fun undo() {
        val memento: UndoRedoItem = undoRedoManager.undo() ?: return
        val start: Int = memento.start
        val end: Int = start + (memento.after?.length ?: 0)

        replace(start, end, memento.before)
        notifyUndoStatusChanged()
    }

    fun redo() {
        val memento: UndoRedoItem = undoRedoManager.redo() ?: return
        val start: Int = memento.start
        val end: Int = start + (memento.before?.length ?: 0)

        replace(start, end, memento.after)
        notifyUndoStatusChanged()
    }

    fun canRedo(): Boolean {
        return undoRedoManager.canRedo()
    }

    fun clearHistory() {
        undoRedoManager.clear()
        notifyUndoStatusChanged()
    }

    fun setMaxHistorySize(size: Int) {
        undoRedoManager.maxSize = size
    }

    private fun replace(start: Int, end: Int, s: CharSequence?) {
        textChangeListener.enabled = false
        editableText.replace(start, end, s)
        textChangeListener.enabled = true
    }

    private fun notifyUndoStatusChanged() {
        val newUndoStatus: Boolean = canUndo()
        val newRedoStatus: Boolean = canRedo()

        if (undoStatus != newUndoStatus) {
            undoStatus = newUndoStatus
            undoStateListener?.onUndoStatusChanged(undoStatus)
        }

        if (redoStatus != newRedoStatus) {
            redoStatus = newRedoStatus

            undoStateListener?.onRedoStatusChanged(redoStatus)
        }
    }

    private inner class TextChangeListener : TextWatcher {
        private var beforeText: CharSequence? = null
        private var afterText: CharSequence? = null
        var enabled: Boolean = true

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (!enabled) {
                return
            }

            beforeText = s?.subSequence(start, start + count)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!enabled) {
                return
            }

            afterText = s?.subSequence(start, start + count)
            undoRedoManager.add(UndoRedoItem(start, beforeText, afterText))
            notifyUndoStatusChanged()
        }

        override fun afterTextChanged(s: Editable?) {}
    }
}