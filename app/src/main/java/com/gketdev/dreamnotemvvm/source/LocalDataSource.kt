package com.gketdev.dreamnotemvvm.source

import com.gketdev.dreamnotemvvm.data.Note
import com.gketdev.dreamnotemvvm.database.NoteDao
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.NullPointerException
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: NoteDao) {

    val notes = flow {
        dao.getAllNotes()
            .catch { emit(null) }
            .collect {
                emit(it)
            }
    }

    fun deleteAllNotes(){
        dao.deleteAllNotes()
    }

    fun addItem(item : Note){
        dao.addNote(item)
    }

}

