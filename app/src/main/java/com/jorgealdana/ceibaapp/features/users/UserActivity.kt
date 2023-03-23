package com.jorgealdana.ceibaapp.features.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jorgealdana.ceibaapp.App
import com.jorgealdana.ceibaapp.databinding.UserActivityBinding
import com.jorgealdana.ceibaapp.features.users.adapters.UserAdapter
import com.jorgealdana.ceibaapp.features.users.adapters.UserAdapterProvider
import com.jorgealdana.ceibaapp.features.users.viewModel.UserViewModel
import com.jorgealdana.ceibaapp.features.users.viewModel.UserViewModelFactory
import com.jorgealdana.ceibaapp.models.User

class UserActivity : AppCompatActivity() {

    private lateinit var binding: UserActivityBinding
    private lateinit var userAdapterProvider: UserAdapterProvider
    private lateinit var mAdapter: UserAdapter

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as App).userRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        callRequest()
        userViewModel.loadUsers()
    }

    private fun callRequest(){
        userViewModel.users.observe(this) {
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter(){
        userAdapterProvider = object : UserAdapterProvider {
            override fun getListUser(): List<User>? {
                return userViewModel.users.value
            }
        }
        mAdapter = UserAdapter(userAdapterProvider)
        binding.listUserRecycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@UserActivity, RecyclerView.VERTICAL, false)
        }
    }
}