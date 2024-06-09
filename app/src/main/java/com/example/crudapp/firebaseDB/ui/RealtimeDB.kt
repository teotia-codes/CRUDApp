package com.example.crudapp.firebaseDB.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crudapp.firebaseDB.realtimeDB.RealtimeResponse
import com.example.crudapp.firebaseDB.utils.ResultState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RealtimeScreen(
    viewModel: RealtimeViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var context = LocalContext.current
    val res = viewModel.res.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        topBar = {
            TopAppBar(title = { Text(text = "") })
        }
    ) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Add New Item") },
                text = {
                    Column {
                        TextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Title") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("Description") }
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        scope.launch {
                            viewModel.insert(
                                RealtimeResponse.RealtimeItems(
                                    title = title,
                                    description = description
                                )
                            ).collect {
                                when (it) {
                                    is ResultState.Success -> {
                                        Toast.makeText(context, it.data, Toast.LENGTH_SHORT).show()
                                    }

                                    is ResultState.Failure -> {
                                        Toast.makeText(
                                            context,
                                            it.msg.toString(),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    is ResultState.Loading -> {
                                        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                        }
                        showDialog = false
                    }) {
                        Text(text = "Add")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            )
            if (res.item.isNotEmpty()) {
                LazyColumn {
                    items(res.item,
                        key = {
                            it.key
                        }) { item ->

                    }
                }
            }
        }
    }
}