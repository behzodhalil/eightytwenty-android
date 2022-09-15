package uz.behzod.eightytwenty.features.category_note

import androidx.recyclerview.widget.DiffUtil
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel

object CategoryNoteDiffUtil : DiffUtil.ItemCallback<NoteCategoryDomainModel>() {

    override fun areItemsTheSame(
        oldItem: NoteCategoryDomainModel,
        newItem: NoteCategoryDomainModel
    ): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(
        oldItem: NoteCategoryDomainModel,
        newItem: NoteCategoryDomainModel
    ): Boolean {
        return oldItem == newItem
    }
}