package uz.behzod.eightytwenty.features.new_task

sealed interface NewNoteUiState {
    object Success : NewNoteUiState
    object Failure : NewNoteUiState
    object Empty : NewNoteUiState
    object Loading : NewNoteUiState
}

fun failure(): NewNoteUiState {
    return NewNoteUiState.Failure
}

fun success(): NewNoteUiState {
    return NewNoteUiState.Success
}

fun loading(): NewNoteUiState {
    return NewNoteUiState.Loading
}

