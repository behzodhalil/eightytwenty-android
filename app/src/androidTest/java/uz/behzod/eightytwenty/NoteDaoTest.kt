package uz.behzod.eightytwenty

import android.content.Context
import android.nfc.Tag
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import uz.behzod.eightytwenty.data.local.dao.NoteDao
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import java.time.ZonedDateTime

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: EightyTwentyDatabase
    private lateinit var noteDao: NoteDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(
            context,
            EightyTwentyDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        Log.i(TAG, "Database is initialized")
        noteDao = database.getNoteDao()
        Log.i(TAG, "Dao is initialized")
    }

    @After
    fun cleanUp() {
        database.close()
        Log.i(TAG, "Database is closed")
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNotes() = runBlocking {
        Log.i(TAG, "InsertAndGetNotes() test is started")
        val time = ZonedDateTime.now()
        Log.i(TAG, "Time is $time")
        val entity = NoteEntity(1, "Google Manager", "Google Manager", time, false, 1)
        Log.i(TAG, "Entity value is ${entity.categoryId}, ${entity.title}")
        noteDao.insert(entity)
        Log.i(TAG, "Note is inserted")
        val notes = noteDao.fetchAllNotes().asLiveData().getOrAwaitValue()
        Log.i(TAG, "Notes is ${notes.contains(entity)}")
        Assert.assertEquals(1, notes.size)
        notes.forEach {
            Log.i(TAG, "Expected data is $it")
        }

    }

    @Test
    @Throws(Exception::class)
    fun updateAndGetNotes() = runBlocking {
        Log.i(TAG, "UpdateAndGetNotes() test is started")
        val time = ZonedDateTime.now()
        val entity = NoteEntity(1, "Google Manager", "Google Manager", time, false, 1)
        noteDao.insert(entity)
        Log.i(TAG, "Inserted data is ${entity.title}")
        val updatedEntity = NoteEntity(1, "Cloud Manager", "Google Manager", time, false, 1)
        noteDao.update(updatedEntity)
        Log.i(TAG, "Updated data is ${updatedEntity.title}")
        val notes = noteDao.fetchAllNotes().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1, notes.size)
        notes.forEach {
            Log.i(TAG, "After updated data is $it")
        }
    }

    @Test
    fun deleteAndGetNotes() = runBlocking {
        Log.i(TAG, "DeleteAndGetNotes() test is started")
        val time = ZonedDateTime.now()
        val entity = NoteEntity(1, "Google Manager", "Google Manager", time, false, 1)
        noteDao.insert(entity)
        val notes = noteDao.fetchAllNotes().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1, notes.size)
        noteDao.delete(entity)
        val afterDeletedNotes = noteDao.fetchAllNotes().asLiveData().getOrAwaitValue()
        Assert.assertEquals(0,afterDeletedNotes.size)
    }

    companion object {
        private const val TAG = "NoteDaoTest"
    }
}