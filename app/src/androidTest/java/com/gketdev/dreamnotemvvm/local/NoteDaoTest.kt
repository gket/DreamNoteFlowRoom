package com.gketdev.dreamnotemvvm.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnitRunner
import app.cash.turbine.test
import com.gketdev.dreamnotemvvm.data.Note
import com.gketdev.dreamnotemvvm.database.NoteDao
import com.gketdev.dreamnotemvvm.database.NoteDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.time.ExperimentalTime

@ExperimentalTime
@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    private lateinit var database: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getNoteDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertNote() = runBlocking {
        val noteItem = Note(1, "Test Note", "Test", System.currentTimeMillis(), "tag", 50)
        dao.addNote(noteItem)
        dao.getAllNotes().test {
            val item = awaitItem()
            if (item != null) {
                assert(item.contains(noteItem))
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun deleteNote() = runBlocking {
        val noteItem = Note(noteId = 0, "Test Note", "Test", System.currentTimeMillis(), "tag", 50)
        dao.addNote(noteItem)
        dao.deleteNote(noteItem)
        dao.getAllNotes().test {
            val item = awaitItem()
            assertFalse(item.contains(noteItem))
            cancelAndIgnoreRemainingEvents()
        }
    }

}