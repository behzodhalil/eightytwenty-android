package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteRelation

interface FetchAllNoteRelation {
    fun execute(): Flow<List<NoteRelation>>
}