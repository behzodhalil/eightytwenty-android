package uz.behzod.eightytwenty.features.note

sealed interface NoteViewEffect {
    object NewNoteViewEffect: NoteViewEffect
    object CategoryViewEffect: NoteViewEffect
    object DetailViewEffect: NoteViewEffect
    object SearchViewEffect: NoteViewEffect
}