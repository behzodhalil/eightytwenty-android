package uz.behzod.eightytwenty

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import uz.behzod.eightytwenty.data.local.dao.NoteCategoryDao
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteCategoryDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: EightyTwentyDatabase
    private lateinit var categoryDao: NoteCategoryDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            EightyTwentyDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        categoryDao = database.getNoteCategoryDao()
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun insertAndGetCategories() = runBlocking {
        val entity = createNoteCategory()
        categoryDao.insert(entity)
        val categories = categoryDao.fetchAllCategories().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1, categories.size)
    }

    @Test
    fun updateAndGetCategories() = runBlocking {
        val entity = createNoteCategory()
        categoryDao.insert(entity)
        val categories = categoryDao.fetchAllCategories().asLiveData().getOrAwaitValue()
        categories.forEach {
            Assert.assertEquals("All", it.name)
        }
        val expected = NoteCategoryEntity(1, "Unread", 1)
        categoryDao.update(expected)
        val updatedCategories = categoryDao.fetchAllCategories().asLiveData().getOrAwaitValue()
        updatedCategories.forEach {
            Assert.assertEquals("Unread", it.name)
        }

    }

    @Test
    fun incrementNoteAndGetCategories() = runBlocking {
        val entity = NoteCategoryEntity(1, "All", 0)
        categoryDao.insert(entity)
        categoryDao.incrementNoteCount(1)
        val categories = categoryDao.fetchAllCategories().asLiveData().getOrAwaitValue()
        categories.forEach {
            Assert.assertEquals(1, it.count)
        }
    }

    @Test
    fun decrementNoteAndGetCategories() = runBlocking {
        val entity = NoteCategoryEntity(1, "All", 1)
        categoryDao.insert(entity)
        categoryDao.decrementNoteCount(1)

        val categories = categoryDao.fetchAllCategories().asLiveData().getOrAwaitValue()
        categories.forEach {
            Assert.assertEquals(0, it.count)
        }
    }

    @Test
    fun fetchAllCategories() = runBlocking {
        val entity = createNoteCategory()
        categoryDao.insert(entity)

        val categories = categoryDao.fetchAllCategories().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1, categories.size)
    }
}