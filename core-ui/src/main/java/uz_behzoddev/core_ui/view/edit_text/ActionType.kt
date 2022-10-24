package uz_behzoddev.core_ui.view.edit_text

sealed interface ActionType {
    object Insert : ActionType
    object Delete : ActionType
    object Paste : ActionType
    object Undefined : ActionType
}

sealed interface UndoRedoEvent
object Insert: UndoRedoEvent
object Delete: UndoRedoEvent
object Paste: UndoRedoEvent