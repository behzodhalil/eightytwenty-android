package uz.behzod.eightytwenty.domain.interactor.note

import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import javax.inject.Inject

class DefaultMoveToGroupNote @Inject constructor(
    private val noteRepository: NoteRepository,
    private val noteCategoryRepository: NoteCategoryRepository
): MoveToGroupNote {

    override suspend fun execute(notes: List<NoteDomainModel>, groupUid: Long) {
        notes.forEach { note ->
            if (note.categoryId != groupUid) {
                noteCategoryRepository.decrementNoteCount(note.categoryId)
                val currentNote = note.copy(categoryId = groupUid).asEntity()
                noteRepository.updateNote(currentNote)
                noteCategoryRepository.incrementNoteCount(groupUid)
            }
        }
    }
}