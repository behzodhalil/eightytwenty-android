package uz.behzod.eightytwenty.domain.interactor.pill

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity

interface FetchPills {
    fun execute(): Flow<List<PillEntity>>
}