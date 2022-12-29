package uz.behzod.eightytwenty.domain.interactor.note

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteFTS

interface SearchNoteFTS {
    fun execute(query: String): Flow<List<NoteFTS>>
}
