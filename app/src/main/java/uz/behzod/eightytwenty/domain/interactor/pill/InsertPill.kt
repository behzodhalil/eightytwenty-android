package uz.behzod.eightytwenty.domain.interactor.pill

import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity

interface InsertPill {
    suspend fun execute(pill: PillEntity)
}