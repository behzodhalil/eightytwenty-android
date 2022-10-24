package uz.behzod.undo_redo

internal class UndoRedoItem constructor(
    val start: Int,
    val before: CharSequence?,
    val after: CharSequence?
)
