package uz.behzod.eightytwenty

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.behzod.eightytwenty.data.local.dao.WaterDao
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

@RunWith(AndroidJUnit4::class)
@SmallTest
class WaterDaoTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var db: EightyTwentyDatabase
    private lateinit var waterDao: WaterDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            EightyTwentyDatabase::class.java
        ).allowMainThreadQueries().build()
        waterDao = db.getWaterDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun check_insert() = runBlocking{
        val water = WaterEntity(quantity = 400L, timestamp = "2022.11.10")
        waterDao.insert(water)
        val waters = waterDao.fetchWaters().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1,waters.size)
    }

    @Test
    fun check_update() = runBlocking {
        val water = WaterEntity(quantity = 400L, timestamp = "2022.11.10",1)
        val updWater = WaterEntity(500L, timestamp = "2022.11.10",1)
        waterDao.insert(water)
        waterDao.update(updWater)
        val waters = waterDao.fetchWaters().asLiveData().getOrAwaitValue()
        Assert.assertEquals(updWater,waters.first())
    }

    @Test
    fun check_delete() = runBlocking {
        val water = WaterEntity(quantity = 400L, timestamp = "2022.11.10")
        waterDao.insert(water)
        waterDao.delete(water)
        val waters = waterDao.fetchWaters().asLiveData().getOrAwaitValue()
        Assert.assertEquals(0,waters.size)
    }

    @Test
    fun check_fetch_all_waters() = runBlocking {
        val water1 = WaterEntity(400L,"2022.11.10")
        val water2 = WaterEntity(500L,"2022.12.20")
        val water3 = WaterEntity(600L,"2022.06.10")
        waterDao.insert(water1)
        waterDao.insert(water2)
        waterDao.insert(water3)
        val waters = waterDao.fetchWaters().asLiveData().getOrAwaitValue()
        Assert.assertEquals(3,waters.size)
    }

}