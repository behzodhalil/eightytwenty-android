package uz.behzod.eightytwenty

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uz.behzod.eightytwenty.data.local.dao.NoteCategoryDao
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteCategoryDaoTest {

    private lateinit var database: EightyTwentyDatabase
    private lateinit var categoryDao: NoteCategoryDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            EightyTwentyDatabase::class.java
        ).build()
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
        val categories = categoryDao.fetchAllCategories()
    }

    @Test
    fun updateAndGetCategories() = runBlocking {

    }

    @Test
    fun incrementNoteAndGetCategories() = runBlocking {

    }

    @Test
    fun decrementNoteAndGetCategories() = runBlocking {

    }

    @Test
    fun fetchAllCategories() = runBlocking {

    }
}