package com.jorgealdana.ceibaapp.features.users.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgealdana.ceibaapp.features.users.repository.UsersRepository
import com.jorgealdana.ceibaapp.models.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UsersRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> = _users

    private fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun checkIfEmpty() = viewModelScope.launch {
        _users.value = repository.getAllUsersFromDB()
        if (_users.value.isNullOrEmpty()) {
            loadUsers()
        }
    }

    fun filterItems(text: String): List<User>? {
        return if (text.isEmpty()) {
            _users.value
        } else
            _users.value?.filter { it.name.contains(text, true) }
    }

    private suspend fun loadUsers() {
        viewModelScope.launch {
            val result = repository.getUsers()
            _users.value = result
            try {
                _users.value?.forEach { user ->
                    insert(user)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}