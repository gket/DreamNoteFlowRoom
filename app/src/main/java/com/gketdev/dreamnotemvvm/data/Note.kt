package com.gketdev.dreamnotemvvm.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Int = 0,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val tag: String?,
    val notePoint: Int
)
