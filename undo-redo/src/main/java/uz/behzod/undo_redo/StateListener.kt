package uz.behzod.undo_redo

interface UndoStatusListener {
    fun onUndoStatusChanged(canUndo: Boolean)
    fun onRedoStatusChanged(canRedo: Boolean)
}