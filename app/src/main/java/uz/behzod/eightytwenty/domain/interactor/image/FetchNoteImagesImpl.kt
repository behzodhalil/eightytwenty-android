package uz.behzod.eightytwenty.domain.interactor.image

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.domain.repository.ImageRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchNoteImagesImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val dispatcher: IDispatcherProvider
): FetchNoteImages {

    override fun execute(): Flow<List<NoteImageEntity>> {
        return flow {
            imageRepository.fetchNoteImages().collect {
                if (it.isNotEmpty()) {
                    emit(it)
                }
            }
        }.flowOn(dispatcher.io)
    }
}