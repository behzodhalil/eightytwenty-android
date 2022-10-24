package uz_behzoddev.core_ui.view.undoredo

sealed interface UndoRedoType

object Once: UndoRedoType
object All: UndoRedoType