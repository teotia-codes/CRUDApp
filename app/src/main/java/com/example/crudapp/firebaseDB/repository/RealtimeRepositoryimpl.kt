package com.example.crudapp.firebaseDB.repository

import com.example.crudapp.firebaseDB.realtimeDB.RealtimeResponse
import com.example.crudapp.firebaseDB.realtimeDB.repository.RealtimeRepository
import com.example.crudapp.firebaseDB.utils.ResultState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RealtimeRepositoryimpl @Inject constructor(
    private val db: DatabaseReference,
) : RealtimeRepository {
    override fun insert(item: RealtimeResponse.RealtimeItems): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)
            db.push().setValue(
                item
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(ResultState.Success("Data inserted sucessfully"))
                }

            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
            awaitClose {
                close()
            }
        }

    override fun getItems(): Flow<ResultState<List<RealtimeResponse>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.map { dataSnapshot ->
                    RealtimeResponse(
                        dataSnapshot.getValue(RealtimeResponse.RealtimeItems::class.java),
                        key = dataSnapshot.key.toString()
                    )
                }
                trySend(ResultState.Success(items))

            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }
        }
        db.addValueEventListener(valueEvent)
        awaitClose {
            db.removeEventListener(valueEvent)
            close()
        }
    }

    override fun delete(key: String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        db.child(key).removeValue()
            .addOnCompleteListener {
                trySend(ResultState.Success("item deleted successfully"))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun update(res: RealtimeResponse): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        val map = HashMap<String, Any>()
        map["title"] = res.item?.title!!
        map["description"] = res.item.description
        db.child(res.key).updateChildren(map)
            .addOnCompleteListener {
                trySend(ResultState.Success("item updated successfully"))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }
}