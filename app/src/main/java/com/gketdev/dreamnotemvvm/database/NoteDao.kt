package com.gketdev.dreamnotemvvm.database

import androidx.room.*
import com.gketdev.dreamnotemvvm.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY notePoint ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Insert
    fun addNote(item: Note)

    @Delete
    fun deleteNote(item: Note)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()
}


