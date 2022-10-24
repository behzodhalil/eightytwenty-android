package uz_behzoddev.core_ui.view.edit_text

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher

class UndoRedoListener(
    private val isChanged: Boolean
) : TextWatcher {

    // CharSequence:
    // Represents a readable sequence of [Char] values.
    private var afterText: CharSequence? = null
    private var beforeText: CharSequence? = null
    private var lastActionType = ActionType.Undefined
    private var lastActionTime: Long = 0L

    override fun beforeTextChanged(text: CharSequence, start: Int, end: Int, count: Int) {
        beforeText = text.subSequence(startIndex = start, endIndex = start + count)
    }

    override fun onTextChanged(text: CharSequence, start: Int, end: Int,count: Int) {
        afterText = text.subSequence(startIndex = start, endIndex = start + count)

    }

    override fun afterTextChanged(p0: Editable?) {
        TODO("Not yet implemented")
    }

    private fun makeBatch(start: Int) {
        val actType = getActionType()

    }

    private fun getActionType(): ActionType {
        return if(!TextUtils.isEmpty(beforeText) && TextUtils.isEmpty(afterText)) {
            ActionType.Delete
        } else if(TextUtils.isEmpty(beforeText) && !TextUtils.isEmpty(afterText)) {
            ActionType.Insert
        } else {
            ActionType.Paste
        }
    }
}