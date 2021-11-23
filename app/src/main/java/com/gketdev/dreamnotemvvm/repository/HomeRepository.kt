package com.gketdev.dreamnotemvvm.repository

import com.gketdev.dreamnotemvvm.data.Note
import com.gketdev.dreamnotemvvm.data.Result
import com.gketdev.dreamnotemvvm.source.DataSourceMapper
import com.gketdev.dreamnotemvvm.source.LocalDataSource
import com.gketdev.dreamnotemvvm.source.RemoteDataSource
import kotlinx.coroutines.flow.*

class HomeRepository(
    private val dataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun getAllNotes(): Flow<Result<List<Note>>> =
        dataSource.notes.map {
            DataSourceMapper.mapData(it)
        }

    fun getNewNotes(): Flow<Note> = flow {
        remoteDataSource.noteFromFakeApi.collect {
            emit(it)
        }
    }

    fun deleteNotes(){
        dataSource.deleteAllNotes()
    }

    fun addNote(item : Note){
        dataSource.addItem(item)
    }


}


