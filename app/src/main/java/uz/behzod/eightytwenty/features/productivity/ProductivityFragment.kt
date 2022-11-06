package uz.behzod.eightytwenty.features.productivity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kr.sns.ui_expandable_view.ExpandableSelectionView
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.databinding.FragmentProductivityBinding
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.features.setting.SettingFragment
import uz.behzod.eightytwenty.utils.extension.navigateTo
import uz.behzod.eightytwenty.utils.extension.transaction
import uz.behzod.eightytwenty.utils.view.viewBinding

@AndroidEntryPoint
class ProductivityFragment : Fragment(R.layout.fragment_productivity) {

    private val binding: FragmentProductivityBinding by viewBinding(FragmentProductivityBinding::bind)
    private lateinit var noteAdapter: ProductivityAdapter
    private lateinit var habitAdapter: ProductivityHabitAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val url =
            "https://www.pngmart.com/files/12/Boy-Emoji-Avatar-PNG.png"
        binding.avatarView.load(url)
        setupRecyclerView()
        binding.btnStart.setOnClickListener { route(ProductivityRoute.SelectProductivityRoute) }
        binding.avatarView.setOnClickListener { route(ProductivityRoute.SettingRoute) }
    }

    private fun setupRecyclerView() {
        val notes = listOf(
            NoteDomainModel(id = 1, title = "Test1", description = "Test1"),
            NoteDomainModel(id = 2, title = "Test2", description = "Test2")
        )

        val habits = listOf(
            HabitDomainModel("Test", description = "test"),
            HabitDomainModel("Test", description = "test")
        )

        habitAdapter = ProductivityHabitAdapter(habits, "Привычки", 1)
        noteAdapter = ProductivityAdapter(notes, "Заметки", 3)
        binding.rvConcatAdapter.setAdapter(noteAdapter)
        binding.rvConcatAdapter.setState(ExpandableSelectionView.State.Expanded)
        binding.rvHabitsAdapter.setAdapter(habitAdapter)
        binding.rvHabitsAdapter.setState(ExpandableSelectionView.State.Expanded)
    }

    private fun observeState() {

    }

    private fun renderState() {

    }

    private fun route(route: ProductivityRoute) {
        when (route) {
            ProductivityRoute.SelectProductivityRoute -> {
                val direction =
                    ProductivityFragmentDirections.actionProductivityFragmentToSelectProductivityFragment()
                navigateTo(direction)
            }
            ProductivityRoute.SettingRoute -> {
                val direction = SettingFragment.instance()
                transaction(direction)
            }
        }
    }


}