package uz.behzod.eightytwenty.features.new_note

sealed interface NoteGroupRoute {
    object Back: NoteGroupRoute
    object Cancel: NoteGroupRoute
    object AddNoteGroup: NoteGroupRoute
}
