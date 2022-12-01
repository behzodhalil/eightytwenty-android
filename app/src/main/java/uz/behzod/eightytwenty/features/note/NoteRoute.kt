package uz.behzod.eightytwenty.features.note

sealed interface NoteRoute {
    object NoteFolder: NoteRoute
    object AddNote: NoteRoute
    object SearchNote: NoteRoute
    object DetailNote: NoteRoute
}
