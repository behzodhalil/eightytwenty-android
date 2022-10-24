package uz.behzod.undo_redo

import java.util.*

internal class CareTaker {
    private val mementos: LinkedList<Memento> = LinkedList()

    var maxSize: Int = -1
        set(s) {
            field = s
            trim()
        }

    private var pos: Int = 0

    fun clear() {
        pos = 0
        mementos.clear()
    }

    fun add(memento: Memento) {
        while (mementos.size > pos) {
            mementos.removeLast()
        }

        mementos.add(memento)
        pos++

        trim()
    }

    fun undo(): Memento? {
        return if (pos == 0) null else mementos[--pos]
    }

    fun canUndo(): Boolean {
        return pos > 0
    }

    fun redo(): Memento? {
        return if (pos == mementos.size) null else mementos[pos++]
    }

    fun canRedo(): Boolean {
        return pos < mementos.size
    }

    private fun trim() {
        if (maxSize < 0) {
            return
        }

        while (mementos.size > maxSize) {
            mementos.removeFirst()
            pos--
        }

        if (pos < 0) {
            pos = 0
        }
    }
}