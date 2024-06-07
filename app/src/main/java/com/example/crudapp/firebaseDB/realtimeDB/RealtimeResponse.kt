package com.example.crudapp.firebaseDB.realtimeDB

data class RealtimeResponse(
    val item: RealtimeItems?,
    val key: String,
){
    data class RealtimeItems(
        val title: String,
        val description: String
    )
}
