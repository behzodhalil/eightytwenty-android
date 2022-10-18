package uz.behzod.eightytwenty.features.category_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.note_category.FetchAllCategories
import uz.behzod.eightytwenty.domain.interactor.note_category.InsertNoteCategory
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel
import javax.inject.Inject

@HiltViewModel
class CategoryNoteViewModel @Inject constructor(
    private val iInsertCategoryNote: InsertNoteCategory,
    iFetchAllCategory: FetchAllCategories
) : ViewModel() {

    private var _uiState: MutableStateFlow<CategoryNoteUIState> =
        MutableStateFlow(CategoryNoteUIState.Loading)
    val uiState: Flow<CategoryNoteUIState> = _uiState.asStateFlow()

    fun insertNoteCategory(value: NoteCategoryDomainModel) {
        viewModelScope.launch {
            iInsertCategoryNote.invoke(value)
        }
    }

    init {
        iFetchAllCategory.invoke()
            .onEach { result ->
            _uiState.value = CategoryNoteUIState.Loading
            if (result.isEmpty()) {
                _uiState.value = CategoryNoteUIState.Empty
            } else {
                _uiState.value = CategoryNoteUIState.Success(result)
            } }
            .launchIn(viewModelScope)

    }
}