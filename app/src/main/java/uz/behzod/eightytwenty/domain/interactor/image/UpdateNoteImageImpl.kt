package uz.behzod.eightytwenty.domain.interactor.image

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.domain.repository.ImageRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class UpdateNoteImageImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val dispatcher: IDispatcherProvider
): UpdateNoteImage {

    override suspend fun execute(value: NoteImageEntity) {
        return withContext(dispatcher.io) {
            imageRepository.updateNoteImage(value)
        }
    }
}