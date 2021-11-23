package com.gketdev.dreamnotemvvm.source

import com.gketdev.dreamnotemvvm.data.ErrorCode
import com.gketdev.dreamnotemvvm.data.Note
import com.gketdev.dreamnotemvvm.data.Result

object DataSourceMapper {

    fun mapData(data: List<Note>?): Result<List<Note>> {
        return when {
            data == null -> {
                Result.Error(
                    message = "Null",
                    code = ErrorCode.NULL_LIST_ERROR
                )
            }
            data.isEmpty() -> {
                Result.Error(
                    message = "Empty List",
                    code = ErrorCode.EMPTY_LIST_ERROR
                )
            }
            else -> {
                Result.Success(data)
            }
        }
    }
}