package com.example.crudapp.firebaseDB.repository

import com.example.crudapp.firebaseDB.FirestoreModelResponse
import com.example.crudapp.firebaseDB.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoredbRepoimpl @Inject constructor(

) :FirestoreRepository {
    override fun insert(item: FirestoreModelResponse.FirestoreItem): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override fun getItems(): Flow<ResultState<List<FirestoreModelResponse>>> {
        TODO("Not yet implemented")
    }

    override fun delete(key: String): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override fun update(item: FirestoreModelResponse): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }
}