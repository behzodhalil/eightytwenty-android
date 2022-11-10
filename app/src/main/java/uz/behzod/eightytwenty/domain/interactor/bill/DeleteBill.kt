package uz.behzod.eightytwenty.domain.interactor.bill

import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity

interface DeleteBill {
    suspend fun execute(bill: BillEntity)
}