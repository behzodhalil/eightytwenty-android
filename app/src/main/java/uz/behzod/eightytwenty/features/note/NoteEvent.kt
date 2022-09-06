package uz.behzod.eightytwenty.features.note

sealed interface NoteEvent {
    object NewNoteEvent: NoteEvent
    object CategoryEvent: NoteEvent
    object DetailEvent: NoteEvent
    object SearchEvent: NoteEvent
}