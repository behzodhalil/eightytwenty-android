package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Embedded

data class NoteWithMatchInfo(
    @Embedded
    val note: NoteEntity,
    val matchInfo: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NoteWithMatchInfo

        if (note != other.note) return false
        if (!matchInfo.contentEquals(other.matchInfo)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = note.hashCode()
        result = 31 * result + matchInfo.contentHashCode()
        return result
    }
}
