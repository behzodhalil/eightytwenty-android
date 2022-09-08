package uz.behzod.eightytwenty.features.new_note

sealed interface NewNoteEvent {
    object SavedEvent: NewNoteEvent
    object UndoEvent: NewNoteEvent
    object RedoEvent: NewNoteEvent
}