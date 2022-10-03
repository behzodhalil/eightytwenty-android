package uz.behzod.eightytwenty.features.note

import uz.behzod.eightytwenty.core.state.ViewEffect

sealed interface NoteViewEffect : ViewEffect {
    object NewNoteClickViewEffect: NoteViewEffect
    object CategoryViewEffect: NoteViewEffect
    object DetailViewEffect: NoteViewEffect
    object SearchViewEffect: NoteViewEffect
}