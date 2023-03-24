package com.jorgealdana.ceibaapp.features.users

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jorgealdana.ceibaapp.App
import com.jorgealdana.ceibaapp.databinding.UserActivityBinding
import com.jorgealdana.ceibaapp.features.posts.PostActivity
import com.jorgealdana.ceibaapp.features.users.adapters.UserAdapter
import com.jorgealdana.ceibaapp.features.users.adapters.UserAdapterProvider
import com.jorgealdana.ceibaapp.features.users.viewModel.UserViewModel
import com.jorgealdana.ceibaapp.features.users.viewModel.UserViewModelFactory
import com.jorgealdana.ceibaapp.models.User
import com.jorgealdana.ceibaapp.utils.Constants

class UserActivity : AppCompatActivity(), UserAdapterProvider.OnItemClickListener {

    private lateinit var binding: UserActivityBinding
    private lateinit var userAdapterProvider: UserAdapterProvider
    private lateinit var mAdapter: UserAdapter
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as App).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar((binding.materialToolbar as Toolbar))
        initAdapter()
        callRequest()
        userViewModel.checkIfEmpty()

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    var data = result.data
                }
            }
    }

    private fun callRequest() {
        userViewModel.users.observe(this) {
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter() {
        userAdapterProvider = object : UserAdapterProvider {
            override fun getListUser(): ArrayList<User>? {
                return userViewModel.users.value?.let { ArrayList(it) }
            }
        }
        mAdapter = UserAdapter(userAdapterProvider, this)
        binding.listUserRecycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@UserActivity, RecyclerView.VERTICAL, false)
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra("user", Gson().toJson(userViewModel.users?.value?.get(position)))
        launcher.launch(intent)
    }
}