package uz.behzod.eightytwenty.domain.interactor.image

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.domain.repository.ImageRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchImagesByNoteByNoteUidImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val dispatcher: IDispatcherProvider
): FetchImagesByNoteByNoteUid {

    override fun execute(uid: Long): Flow<List<NoteImageEntity>> {
        return flow {
            imageRepository.fetchImagesByNoteByNoteUid(uid).collect {
                if (it.isNotEmpty()) {
                    emit(it)
                }
            }
        }.flowOn(dispatcher.io)
    }
}