package uz.behzod.undo_redo

import java.util.*

internal class UndoRedoManager {
    private val items: LinkedList<UndoRedoItem> = LinkedList()

    var maxSize: Int = -1
        set(s) {
            field = s
            trim()
        }

    private var position: Int = 0

    fun clear() {
        position = 0
        items.clear()
    }

    fun add(memento: UndoRedoItem) {
        while (items.size > position) {
            items.removeLast()
        }

        items.add(memento)
        position++

        trim()
    }

    fun undo(): UndoRedoItem? {
        return if (position == 0) null else items[--position]
    }

    fun canUndo(): Boolean {
        return position > 0
    }

    fun redo(): UndoRedoItem? {
        return if (position == items.size) null else items[position++]
    }

    fun canRedo(): Boolean {
        return position < items.size
    }

    private fun trim() {
        if (maxSize < 0) {
            return
        }

        while (items.size > maxSize) {
            items.removeFirst()
            position--
        }

        if (position < 0) {
            position = 0
        }
    }
}