package uz.behzod.eightytwenty.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.entities.Frequency
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.interactor.habit_recommend.InsertHabitRecommends
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltWorker
class HabitRecommendWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    @Inject
    lateinit var insertHabitRecommends: InsertHabitRecommends

    private val habitRecommends = listOf(
        HabitRecommendDomainModel(
            title = "Встать раньше 7 утра",
            description = "Встать раньше 7 утра",
            perDayGoalCount = 1,
            endGoalCount = 60,
            frequency = Frequency.WEEK,
            timestamp = ZonedDateTime.now().toString().toLong(),
            color = "Green",
            isComplete = false,
            category = "Our choice"
        ), HabitRecommendDomainModel(
            title = "Выпить стакан воды",
            description = "Выпить стакан воды",
            perDayGoalCount = 1,
            endGoalCount = 60,
            frequency = Frequency.DAILY,
            timestamp = ZonedDateTime.now().toString().toLong(),
            color = "Blue",
            isComplete = false,
            category = "Our choice"
        ), HabitRecommendDomainModel(
            title = "Встать раньше 7 утра",
            description = "Встать раньше 7 утра",
            perDayGoalCount = 1,
            endGoalCount = 60,
            frequency = Frequency.WEEK,
            timestamp = ZonedDateTime.now().toString().toLong(),
            color = "Green",
            isComplete = false,
            category = "Our choice"
        ), HabitRecommendDomainModel(
            title = "Почистить зубы",
            description = "Почистить зубы",
            perDayGoalCount = 2,
            endGoalCount = 60,
            frequency = Frequency.WEEK,
            timestamp = ZonedDateTime.now().toString().toLong(),
            color = "Light Green",
            isComplete = false,
            category = "Our choice"
        ), HabitRecommendDomainModel(
            title = "Встать раньше 7 утра",
            description = "Встать раньше 7 утра",
            perDayGoalCount = 1,
            endGoalCount = 60,
            frequency = Frequency.WEEK,
            timestamp = ZonedDateTime.now().toString().toLong(),
            color = "Green",
            isComplete = false,
            category = "Our choice"
        ), HabitRecommendDomainModel(
            title = "Читать",
            description = "Читать ",
            perDayGoalCount = 25,
            endGoalCount = 100,
            frequency = Frequency.WEEK,
            timestamp = ZonedDateTime.now().toString().toLong(),
            color = "Blue",
            isComplete = false,
            category = "Our choice"
        )
    )

    override suspend fun doWork() = coroutineScope {
        try {
            val database = EightyTwentyDatabase.invoke(applicationContext)
            database.getHabitRecommendDao()
                .insertHabitRecommends(habitRecommends.map { it.asEntity() })
            Log.d("Worker", "doWork method was called successfully")
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}