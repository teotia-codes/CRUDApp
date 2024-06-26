package com.example.crudapp.firebaseDB

data class FirestoreModelResponse(
    val item:FirestoreItem?,
    val key:String? = ""
){

    data class FirestoreItem(
        val title:String? = "",
        val description:String? = ""
    )

}