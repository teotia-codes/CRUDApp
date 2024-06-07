package com.example.crudapp.firebaseDB.repository

import com.example.crudapp.firebaseDB.FirestoreModelResponse
import com.example.crudapp.firebaseDB.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface FirestoreRepository {

    fun insert(
        item:FirestoreModelResponse.FirestoreItem
    ) : Flow<ResultState<String>>

    fun getItems() : Flow<ResultState<List<FirestoreModelResponse>>>

    fun delete(key:String) : Flow<ResultState<String>>

    fun update(
        item:FirestoreModelResponse
    ) : Flow<ResultState<String>>

}