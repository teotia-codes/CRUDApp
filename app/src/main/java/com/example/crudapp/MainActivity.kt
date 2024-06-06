package com.example.crudapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.crudapp.ui.theme.CRUDAppTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CRUDAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user: MutableMap<String,Any> = HashMap()
                    user["first"] = "Priyanshu"
                    user["last"] = "Teotia"
                    user["born"] = "2 Nov 2002"
                    FirebaseFirestore.getInstance().collection("users")
                        .add(user)
                        .addOnSuccessListener { documentReference->
                            Log.d("TAQ", "DocumentSnapshot added with ID: " + documentReference.id)
                        }
                        .addOnFailureListener { e->
                            Log.w("TAG", "Error adding document",e)
                        }
                }
            }
        }
    }
}
