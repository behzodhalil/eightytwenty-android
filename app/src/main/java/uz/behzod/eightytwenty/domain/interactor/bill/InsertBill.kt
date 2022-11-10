package uz.behzod.eightytwenty.domain.interactor.bill

import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity

interface InsertBill {
    suspend fun execute(bill: BillEntity): Long
}