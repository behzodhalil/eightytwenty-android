package uz.behzod.undo_redo

internal class Memento constructor(
    val start: Int,
    val before: CharSequence?,
    val after: CharSequence?
)
