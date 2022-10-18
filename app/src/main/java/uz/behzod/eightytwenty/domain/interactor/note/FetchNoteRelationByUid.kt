package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteRelation

interface FetchNoteRelationByUid {
    fun execute(noteUid: Long): Flow<NoteRelation>
}