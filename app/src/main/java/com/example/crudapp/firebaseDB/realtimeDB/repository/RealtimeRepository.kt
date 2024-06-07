package com.example.crudapp.firebaseDB.realtimeDB.repository

import com.example.crudapp.firebaseDB.realtimeDB.RealtimeResponse
import com.example.crudapp.firebaseDB.utils.ResultState
import java.util.concurrent.Flow

interface RealtimeRepository {
    fun insert(
        item:RealtimeResponse.RealtimeItems
    ) :  kotlinx.coroutines.flow.Flow<ResultState<String>>
    fun getItems() : kotlinx.coroutines.flow.Flow<ResultState<List<RealtimeResponse>>>

    fun delete(
        key:String)
    :kotlinx.coroutines.flow.Flow<ResultState<String>>
    fun update(
        res: RealtimeResponse)
    :kotlinx.coroutines.flow.Flow<ResultState<String>>
}