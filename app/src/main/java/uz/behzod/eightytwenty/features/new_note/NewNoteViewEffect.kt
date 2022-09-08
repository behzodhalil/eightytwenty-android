package uz.behzod.eightytwenty.features.new_note


sealed interface NewNoteViewEffect {
    object SavedViewEffect: NewNoteViewEffect
    object UndoViewEffect: NewNoteViewEffect
    object RedoViewEffect: NewNoteViewEffect
}