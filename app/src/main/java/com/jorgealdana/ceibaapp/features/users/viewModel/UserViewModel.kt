package com.jorgealdana.ceibaapp.features.users.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.jorgealdana.ceibaapp.features.users.repository.UsersRepository
import com.jorgealdana.ceibaapp.models.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UsersRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        _users.value = emptyList()
    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val result = repository.getUsers()
                _users.value = result
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}