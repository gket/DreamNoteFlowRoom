package com.gketdev.dreamnotemvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gketdev.dreamnotemvvm.data.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}