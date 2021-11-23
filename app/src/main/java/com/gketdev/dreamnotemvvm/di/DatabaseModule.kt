package com.gketdev.dreamnotemvvm.di

import android.content.Context
import androidx.room.Room
import com.gketdev.dreamnotemvvm.R
import com.gketdev.dreamnotemvvm.database.NoteDao
import com.gketdev.dreamnotemvvm.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            appContext.getString(R.string.app_name)
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: NoteDatabase): NoteDao {
        return database.getNoteDao()
    }
}