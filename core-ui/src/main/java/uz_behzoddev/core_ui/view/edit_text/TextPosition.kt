package uz_behzoddev.core_ui.view.edit_text

data class TextPosition(
    var text: UndoRedoItem,
    var previousPosition: TextPosition?= null,
    var afterPosition: TextPosition? = null
)
