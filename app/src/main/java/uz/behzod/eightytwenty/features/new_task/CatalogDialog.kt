package uz.behzod.eightytwenty.features.new_task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.DialogCatalogBinding
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class CatalogDialog : DialogFragment(R.layout.dialog_catalog) {

    private val binding by viewBinding(DialogCatalogBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}