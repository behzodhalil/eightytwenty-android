package uz.behzod.eightytwenty.domain.interactor.image

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.domain.repository.ImageRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DeleteNoteImageImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val dispatcher: IDispatcherProvider
): DeleteNoteImage {

    override suspend fun execute(value: NoteImageEntity) {
        return withContext(dispatcher.io) {
            imageRepository.deleteNoteImage(value)
        }
    }
}