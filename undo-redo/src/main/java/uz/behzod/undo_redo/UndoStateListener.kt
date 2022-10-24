package uz.behzod.undo_redo

interface UndoStateListener {
    fun onUndoStatusChanged(canUndo: Boolean)
    fun onRedoStatusChanged(canRedo: Boolean)
}