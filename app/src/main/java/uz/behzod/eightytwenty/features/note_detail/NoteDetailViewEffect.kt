package uz.behzod.eightytwenty.features.note_detail

import uz.behzod.eightytwenty.core.state.ViewEffect

sealed interface NoteDetailViewEffect: ViewEffect {
    object NoteViewEffect: NoteDetailViewEffect
}