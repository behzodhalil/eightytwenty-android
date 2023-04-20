package uz.behzod.eightytwenty.data.source

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.data.local.entities.NoteFTS
import uz.behzod.eightytwenty.data.local.entities.NoteWithMatchInfo

interface NoteSourceManager {
    fun searchNoteFTS(query: String): Flow<List<NoteWithMatchInfo>>
}
