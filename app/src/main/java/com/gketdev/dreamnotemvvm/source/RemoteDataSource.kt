package com.gketdev.dreamnotemvvm.source

import com.gketdev.dreamnotemvvm.data.Note
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class RemoteDataSource @Inject constructor() {

    val noteFromFakeApi = flow {
        while (true) {
            delay(2000)
            emit(
                Note(
                    title = "",
                    content = "",
                    timeStamp = 0L,
                    tag = "",
                    notePoint = Random.nextInt(0, 100)
                )
            )
        }
    }

}

