package uz.behzod.eightytwenty.features.new_note

import uz.behzod.eightytwenty.core.state.ViewEffect


sealed interface NewNoteViewEffect: ViewEffect {
    object SavedViewEffect: NewNoteViewEffect
    object UndoViewEffect: NewNoteViewEffect
    object RedoViewEffect: NewNoteViewEffect
}