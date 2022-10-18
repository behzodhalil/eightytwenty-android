package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.ImageRepository
import uz.behzod.eightytwenty.domain.repository.NoteCategoryRepository
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class InsertNoteImpl @Inject constructor(
    private val iNoteRepository: NoteRepository,
    private val iNoteCategoryRepository: NoteCategoryRepository,
    private val iImageRepository: ImageRepository,
    private val dispatcherProvider: IDispatcherProvider
): InsertNote {

    override suspend fun invoke(data: NoteDomainModel, images: List<NoteImageEntity>) {
        return withContext(dispatcherProvider.io + NonCancellable) {
            iNoteCategoryRepository.incrementNoteCount(data.categoryId)
            val noteUid = iNoteRepository.insertNote(data.asEntity())
            images.forEach {
                it.noteUid = noteUid
                iImageRepository.insertNoteImage(it)
            }

            printDebug { "[Test Images] InsertNoteUseCase are $images" }

        }
    }
}