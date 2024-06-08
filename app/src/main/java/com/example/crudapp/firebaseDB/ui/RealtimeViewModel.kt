package com.example.crudapp.firebaseDB.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudapp.firebaseDB.realtimeDB.RealtimeResponse
import com.example.crudapp.firebaseDB.realtimeDB.repository.RealtimeRepository
import com.example.crudapp.firebaseDB.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealtimeViewModel @Inject constructor(
    private val repo: RealtimeRepository,
) : ViewModel() {
    private val _res: MutableState<ItemState> = mutableStateOf(ItemState())
    val res: State<ItemState> = _res

    fun insert(items: RealtimeResponse.RealtimeItems) = repo.insert(items)

    init {
        viewModelScope.launch {
            repo.getItems().collect {
                when (it) {
                    is ResultState.Success -> {
                        _res.value = ItemState(item = it.data)

                    }

                    is ResultState.Failure -> {
                        _res.value = ItemState(
                            error =  it.msg.toString()
                        )

                    }
                    is ResultState.Loading -> {
                        _res.value = ItemState(isLoading = true)

                    }
                }
            }
        }
    }
    fun delete(key: String) = repo.delete(key)
    fun update(items: RealtimeResponse) = repo.update(items)


}

data class ItemState(
    val item: List<RealtimeResponse> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false,
)