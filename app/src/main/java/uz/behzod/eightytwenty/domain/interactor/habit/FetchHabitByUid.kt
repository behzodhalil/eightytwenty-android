package uz.behzod.eightytwenty.domain.interactor.habit

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.HabitDomainModel

interface FetchHabitByUid {
    operator fun invoke(uid: Long): Flow<HabitDomainModel>
}