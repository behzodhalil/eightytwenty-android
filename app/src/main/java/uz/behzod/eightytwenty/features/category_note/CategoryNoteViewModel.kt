package uz.behzod.eightytwenty.features.category_note

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.note_category.DeleteNoteCategory
import uz.behzod.eightytwenty.domain.interactor.note_category.FetchAllCategories
import uz.behzod.eightytwenty.domain.interactor.note_category.InsertNoteCategory
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val defaultInsertCategory: InsertNoteCategory,
    private val defaultDeleteCategory: DeleteNoteCategory,
    defaultAllCategories: FetchAllCategories
) : ReduxViewModel<CategoryState>(initialState = CategoryState.empty) {

    init {
        defaultAllCategories.invoke()
            .onStart { modifyState { state -> state.copy(onLoading = true) } }
            .onEach { categories ->
                if (categories.isNotEmpty()) {
                    modifyState { state ->
                        state.copy(
                            onSuccess = true, onLoading = false, categories = categories
                        )
                    }
                } else {
                    modifyState { state ->
                        state.copy(
                            onLoading = false,
                            onSuccess = false,
                            onEmpty = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun updateCategoryName(value: String) {
        modifyState { state -> state.copy(categoryName = value) }
    }

    fun updateUid(value: Long) {
        modifyState { state -> state.copy(categoryUid = value) }
    }
    fun hasSaved(value: Boolean) {
        modifyState { state -> state.copy(onSaved = value) }
    }

    fun hasFillCategory(value: Boolean) {
        modifyState { state -> state.copy(onFillCategory = value) }
    }

    fun insertCategory() {
        viewModelScope.launch {
            val name = currentState.categoryName
            val category = NoteCategoryDomainModel(name = name)
            runCatching {
                defaultInsertCategory.invoke(category)
            }.onSuccess {
                modifyState { state ->
                    state.copy(
                        onSaved = true
                    )
                }
            }.onFailure {
                modifyState { state ->
                    state.copy(
                        onSaveFailed = true,
                        onSaved = false
                    )
                }
            }
        }
    }

    fun deleteCategory() {
        viewModelScope.launch {
            val title = currentState.categoryName
            val uid = currentState.categoryUid
            val category = NoteCategoryDomainModel(name = title, uid = uid)
            runCatching {
                defaultDeleteCategory.invoke(category)
            }.onSuccess {
                modifyState { state ->
                    state.copy(
                        onDeleted = true
                    )
                }
            }.onFailure {
                modifyState { state ->
                    state.copy(
                        onDeleted = false,
                        onDeleteFailed = true
                    )
                }
            }
        }
    }

    fun hasDeleted(value: Boolean) {
        modifyState { state -> state.copy(onDeleted = false) }
    }
}
