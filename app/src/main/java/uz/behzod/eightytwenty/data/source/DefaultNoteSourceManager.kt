package uz.behzod.eightytwenty.data.source

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.dao.NoteDao
import uz.behzod.eightytwenty.data.local.entities.NoteWithMatchInfo
import javax.inject.Inject

class DefaultNoteSourceManager @Inject constructor(
    private val noteDao: NoteDao
): NoteSourceManager {

    override fun searchNoteFTS(query: String): Flow<List<NoteWithMatchInfo>> {
        return noteDao.searchNoteFts(query)
    }
}
