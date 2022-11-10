package uz.behzod.eightytwenty.domain.interactor.pill

import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity

interface UpdatePill {
    suspend fun execute(pill: PillEntity)
}