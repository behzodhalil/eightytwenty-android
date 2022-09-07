package uz.behzod.eightytwenty

import android.content.Context
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
import uz.behzod.eightytwenty.data.local.dao.NoteCategoryDao
import uz.behzod.eightytwenty.data.local.dao.NoteDao
import uz.behzod.eightytwenty.data.local.db.EightyTwentyDatabase
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManagerImpl
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
@SmallTest
class LocalSourceManagerTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var database: EightyTwentyDatabase
    private lateinit var noteDao: NoteDao
    private lateinit var noteCategoryDao: NoteCategoryDao
    private lateinit var sourceManager: LocalSourceManagerImpl

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            EightyTwentyDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        noteDao = database.getNoteDao()
        noteCategoryDao = database.getNoteCategoryDao()

        sourceManager = LocalSourceManagerImpl(noteDao, noteCategoryDao)
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNotes() = runBlocking {
        // Given
        val note = createNote()
        sourceManager.insertNote(note)

        // When
        val notes = sourceManager.fetchAllNotes().asLiveData().getOrAwaitValue()

        // Then
        Assert.assertEquals(1, notes.size)

    }

    @Test
    @Throws(Exception::class)
    fun updateAndGetNotes() = runBlocking {
        val note = createNote()
        sourceManager.insertNote(note)

        val expected = note.copy(title = "Cloud Manager")
        sourceManager.updateNote(expected)

        val notes = sourceManager.fetchAllNotes().asLiveData().getOrAwaitValue()

        notes.forEach {
            Log.i(TAG,it.title)
            Assert.assertEquals("Cloud Manager",it.title)
        }

    }

    @Test
    @Throws(Exception::class)
    fun deleteAndGetNotes() = runBlocking {
        val note = createNote()
        sourceManager.insertNote(note)
        sourceManager.deleteNote(note)

        val notes = sourceManager.fetchAllNotes().asLiveData().getOrAwaitValue()

        Assert.assertEquals(0,notes.size)
    }

    @Test
    @Throws(Exception::class)
    fun getTrashedNotes() = runBlocking {
        val notes = createTrashedNote()
        sourceManager.insertNote(notes)

        val trashedNotes = sourceManager.fetchTrashedNotes().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1,trashedNotes.size)
    }

    @Test
    @Throws(Exception::class)
    fun checkTrashedNotesIfEmpty() = runBlocking {
        val note = createNote()
        sourceManager.insertNote(note)

        val trashedNotes = sourceManager.fetchTrashedNotes().asLiveData().getOrAwaitValue()
        Assert.assertEquals(0,trashedNotes.size)
    }


    @Test
    @Throws(Exception::class)
    fun insertCategoryAndGetCategories() = runBlocking {
        val category = createNoteCategory()
        sourceManager.insertNoteCategory(category)

        val categories = sourceManager.fetchAllCategories().asLiveData().getOrAwaitValue()
        Assert.assertEquals(1, categories.size)
    }

    @Test
    @Throws(Exception::class)
    fun updateCategoryAndGetCategories() = runBlocking {
        val category = createNoteCategory()
        sourceManager.insertNoteCategory(category)

        val expected = category.copy(name = "Unread")
        sourceManager.updateNoteCategory(expected)

        val categories = sourceManager.fetchAllCategories().asLiveData().getOrAwaitValue()
        categories.forEach {
            Assert.assertEquals("Unread",it.name)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteCategoryAndGetCategories() = runBlocking {
        val category = createNoteCategory()
        sourceManager.insertNoteCategory(category)

        sourceManager.deleteNoteCategory(category)
        val categories = sourceManager.fetchAllCategories().asLiveData().getOrAwaitValue()
        Assert.assertEquals(0,categories.size)
    }

    @Test
    @Throws(Exception::class)
    fun incrementNoteCountAndGetCategories() = runBlocking {
        val category = createNoteCategory()
        sourceManager.insertNoteCategory(category)

        sourceManager.incrementNoteCount(category.id)

        val categories = sourceManager.fetchAllCategories().asLiveData().getOrAwaitValue()
        categories.forEach {
            Assert.assertEquals(2,it.count)
        }
    }

    @Test
    @Throws(Exception::class)
    fun decrementNoteCountAndGetCategories() = runBlocking {
        val category = createNoteCategory()
        sourceManager.insertNoteCategory(category)

        sourceManager.decrementNoteCount(category.id)

        val categories = sourceManager.fetchAllCategories().asLiveData().getOrAwaitValue()
        categories.forEach {
            Assert.assertEquals(0,it.count)
        }
    }

    @Test
    @Throws(Exception::class)
    fun fetchAllCategoriesAndNotes() = runBlocking {
        val note = createNote()
        val category = createNoteCategory()
        sourceManager.insertNoteCategory(category)
        sourceManager.incrementNoteCount(note.categoryId)
        sourceManager.insertNote(note)

        val categoriesAndNotes = sourceManager.fetchAllCategoriesAndNotes().asLiveData().getOrAwaitValue()

        categoriesAndNotes.forEach {
            Log.i(TAG, it.notes[0].toString())
            Assert.assertEquals(1,it.notes.size)
            Log.i(TAG, "Note is ${it.notes[0]}")
            Log.i(TAG,"Note size is ${it.notes.size}")
            Assert.assertEquals(2,it.noteCategoryEntity.count)
        }
    }

    companion object {
        private const val TAG = "LocalSourceManagerTest"
    }

}