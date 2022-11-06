package uz.behzod.eightytwenty.data.local.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.entities.Frequency
import uz.behzod.eightytwenty.data.local.entities.PerDayGoalType
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.di.CallbackScope
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.utils.extension.printDebug
import javax.inject.Inject
import javax.inject.Provider

class HabitRecommendCallback @Inject constructor(
    private val database: Provider<EightyTwentyDatabase>,
    @CallbackScope private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        printDebug { "Callback is called" }
        val dao = database.get().getHabitRecommendDao()
        val taskGroupDao = database.get().getTaskCatalogDao()

        scope.launch {
            dao.insertHabitRecommends(habitRecommends.map { it.asEntity() })
            taskGroupDao.insertTaskCatalog(group)
        }
    }

}

private val habitRecommends = listOf(
    HabitRecommendDomainModel(
        title = "Встать раньше 7 утра",
        description = "Встать раньше 7 утра",
        perDayGoalCount = 1,
        endGoalCount = 60,
        frequency = Frequency.WEEK,
        timestamp = 2022L,
        perDayGoalType = PerDayGoalType.ONCE,
        color = "Green",
        isComplete = false,
        category = "Our Choice"
    ), HabitRecommendDomainModel(
        title = "Выпить стакан воды",
        description = "Выпить стакан воды",
        perDayGoalCount = 1,
        endGoalCount = 60,
        frequency = Frequency.DAILY,
        timestamp = 2022L,
        perDayGoalType = PerDayGoalType.CUP,
        color = "Blue",
        isComplete = false,
        category = "Our Choice"
    ), HabitRecommendDomainModel(
        title = "Встать раньше 7 утра",
        description = "Встать раньше 7 утра",
        perDayGoalCount = 1,
        endGoalCount = 60,
        frequency = Frequency.WEEK,
        timestamp = 2022L,
        perDayGoalType = PerDayGoalType.ONCE,
        color = "Green",
        isComplete = false,
        category = "Health"
    ), HabitRecommendDomainModel(
        title = "Почистить зубы",
        description = "Почистить зубы",
        perDayGoalCount = 2,
        endGoalCount = 60,
        frequency = Frequency.WEEK,
        timestamp = 2022L,
        perDayGoalType = PerDayGoalType.ONCE,
        color = "Light Green",
        isComplete = false,
        category = "Health"
    ), HabitRecommendDomainModel(
        title = "Встать раньше 7 утра",
        description = "Встать раньше 7 утра",
        perDayGoalCount = 1,
        endGoalCount = 60,
        frequency = Frequency.WEEK,
        timestamp = 2022L,
        perDayGoalType = PerDayGoalType.ONCE,
        color = "Green",
        isComplete = false,
        category = "Our Choice"
    ), HabitRecommendDomainModel(
        title = "Читать",
        description = "Читать",
        perDayGoalCount = 25,
        endGoalCount = 100,
        frequency = Frequency.WEEK,
        perDayGoalType = PerDayGoalType.HOUR,
        timestamp = 2022L,
        color = "Blue",
        isComplete = false,
        category = "Study"
    )
)

private val group = TaskCatalogEntity(name = "Входящий")