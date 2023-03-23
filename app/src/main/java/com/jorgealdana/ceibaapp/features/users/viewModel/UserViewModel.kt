package com.jorgealdana.ceibaapp.features.users.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jorgealdana.ceibaapp.features.users.repository.UsersRepository
import com.jorgealdana.ceibaapp.models.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UsersRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData();

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
}