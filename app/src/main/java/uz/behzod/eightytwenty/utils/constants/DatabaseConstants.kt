package uz.behzod.eightytwenty.utils.constants

object DatabaseConstants {
    const val DB_NAME = "eighty_database"

    object Bill {
        const val BILL_TABLE_NAME = "bill_table"
        const val BILL_AMOUNT = "bill_amount"
        const val BILL_TYPE = "bill_type"
        const val BILL_DURATION = "bill_duration"
        const val BILL_NAME = "bill_name"
        const val BILL_TIMESTAMP = "bill_timestamp"
        const val BILL_UID = "bill_uid"
    }

    object Pill {
        const val PILL_TABLE_NAME = "pill_table"
        const val PILL_DURATION = "pill_duration"
        const val PILL_FREQUENCY = "pill_frequency"
        const val PILL_TIMESTAMP = "pill_timestamp"
        const val PILL_DOSE  = "pill_dose"
        const val PILL_NAME = "pill_name"
        const val PILL_FORM = "pill_form"
        const val PILL_UID = "pill_uid"
    }

    object Water {
        const val WATER_TABLE_NAME = "water_table"
        const val WATER_QUANTITY = "water_quantity"
        const val WATER_TIMESTAMP = "water_timestamp"
        const val WATER_FREQUENCY = "water_frequency"
        const val WATER_REMINDER_TIME = "water_reminder_time"
        const val WATER_UID = "water_uid"
    }

    object Task {
        const val TASK_TABLE_NAME = "task_table"

    }

    object Note {}
    object Attachment {}
    object Habit {}
    object Schedule {}
    object TaskFolder {}
    object User {}
    object NoteFolder {}
    object NoteImage {}




    const val NOTE_TABLE_NAME = "note_table"

    const val ATTACHMENT_TABLE_NAME = "attachment_table"

    const val HABIT_TABLE_NAME = "habit_table"

    const val SCHEDULE_TABLE_NAME = "schedule_table"

    const val TASK_FOLDER_TABLE_NAME = "task_folder_table"

    const val USER_TABLE_NAME = "user_table"

    const val NOTE_FOLDER_TABLE_NAME = "note_folder_table"

    const val NOTE_IMAGE_TABLE_NAME = "note_image_table"

}
