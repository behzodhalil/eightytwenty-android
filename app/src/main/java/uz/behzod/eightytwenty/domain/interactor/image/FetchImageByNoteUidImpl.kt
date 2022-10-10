package uz.behzod.eightytwenty.domain.interactor.image

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.domain.repository.ImageRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchImageByNoteUidImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val dispatcher: IDispatcherProvider
): FetchImageByNoteUid {

    override fun execute(uid: Long): Flow<NoteImageEntity> {
        return flow {
            imageRepository.fetchImageByNoteUid(uid).collect { image: NoteImageEntity? ->
                if (image != null) {
                    emit(image)
                }
            }
        }.flowOn(dispatcher.io)
    }
}