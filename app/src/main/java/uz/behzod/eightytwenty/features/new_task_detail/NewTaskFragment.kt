package uz.behzod.eightytwenty.features.new_task_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.google.android.material.dialog.MaterialDialogs
import dagger.hilt.android.AndroidEntryPoint
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.databinding.FragmentNewTaskDetailBinding
import uz.behzod.eightytwenty.utils.view.viewBinding
import java.time.LocalTime

@AndroidEntryPoint
class NewTaskFragment: Fragment(R.layout.fragment_new_task_detail) {

    private val binding by viewBinding(FragmentNewTaskDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    
    private fun setupUi() {
        
    }
    
    private fun initDateOfCompletion() {
        binding.btnSelectDateOfCompletion.setOnClickListener { 
            MaterialDialog(requireContext()).show { 
                lifecycleOwner(viewLifecycleOwner)
                dateTimePicker(
                    requireFutureDateTime = true,
                    currentDateTime =
                )
            }
        }
    }
}