package com.example.crudapp.firebaseDB.repository

import com.example.crudapp.firebaseDB.realtimeDB.RealtimeResponse
import com.example.crudapp.firebaseDB.realtimeDB.repository.RealtimeRepository
import com.example.crudapp.firebaseDB.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RealtimeRepositoryimpl @Inject constructor(): RealtimeRepository {
    override fun insert(item: RealtimeResponse.RealtimeItems): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override fun getItems(): Flow<ResultState<List<RealtimeResponse>>> {
        TODO("Not yet implemented")
    }

    override fun delete(key: String): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override fun update(res: RealtimeResponse): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }
}