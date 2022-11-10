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
import uz.behzod.eightytwenty.data.local.dao.PillDao
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase

@RunWith(AndroidJUnit4::class)
@SmallTest
class PillDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: EightyTwentyDatabase
    private lateinit var pillDao: PillDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, EightyTwentyDatabase::class.java)
            .allowMainThreadQueries().build()
        pillDao = db.getPillDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun check_insert_pill() = runBlocking {
        val pill = createPill()
        pillDao.insert(pill)
        val pills = pillDao.fetchPills().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1, pills.size)
    }

    @Test
    fun check_update_pill() = runBlocking{
        val pill = createPill()
        val pill2 = createPillA()
        pillDao.insert(pill)
        pillDao.update(pill2)
        val pills = pillDao.fetchPills().asLiveData().getOrAwaitValue()
        Assert.assertEquals(pill2,pills.first())
    }

    @Test
    fun check_delete_pill() = runBlocking {
        val pill = createPill()
        pillDao.insert(pill)
        pillDao.delete(pill)
        val pills = pillDao.fetchPills().asLiveData().getOrAwaitValue()
        Assert.assertEquals(0,pills.size)
    }

    @Test
    fun check_fetch_pills()  = runBlocking{
        val pill = createPill()
        pillDao.insert(pill)
        val pills = pillDao.fetchPills().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1, pills.size)
    }
}