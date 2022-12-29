package uz.behzod.eightytwenty.data.source

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteFTS

interface NoteSourceManager {
    fun searchNoteFTS(query: String): Flow<List<NoteFTS>>
}
