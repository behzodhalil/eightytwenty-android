package uz.behzod.eightytwenty.domain.interactor.image

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.domain.repository.ImageRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class InsertNoteImagesImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val dispatcher: IDispatcherProvider
): InsertNoteImages {

    override suspend fun execute(values: List<NoteImageEntity>) {
        return withContext(dispatcher.io) {
            imageRepository.insertNoteImages(values)
        }
    }
}