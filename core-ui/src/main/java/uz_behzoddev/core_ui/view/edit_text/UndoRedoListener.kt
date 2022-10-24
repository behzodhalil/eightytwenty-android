package uz_behzoddev.core_ui.view.edit_text

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LifecycleObserver
import dagger.hilt.android.qualifiers.ApplicationContext

class UndoRedoListener(
    @ApplicationContext context: Context,
    lifecycleObserver: LifecycleObserver,
    private val editText: EditText,

) : TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun afterTextChanged(p0: Editable?) {
        TODO("Not yet implemented")
    }
}