package com.jorgealdana.ceibaapp.features.users

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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
import com.jorgealdana.ceibaapp.utils.DialogUtils

class UserActivity : AppCompatActivity(), UserAdapterProvider {

    private lateinit var binding: UserActivityBinding
    private lateinit var mAdapter: UserAdapter
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var dialogUtils: AlertDialog
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as App).userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar((binding.materialToolbar as Toolbar))
        callRequest()
        initAdapter(emptyList())
        initListeners()
        userViewModel.checkIfEmpty()
    }

    private fun initListeners() {
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // do something
                }
            }

        binding.txtSearchUser.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userViewModel.filterItems(s.toString())?.let {
                    mAdapter.setFilterItems(it)
                    binding.listEmptyTxt.visibility = if (it.isEmpty()) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        })
    }

    private fun callRequest() {
        dialogUtils = DialogUtils.showLoadingDialog(this).also { it.show() }
        userViewModel.users.observe(this) {
            mAdapter.setItems(it)
            if(it.isNotEmpty()) dialogUtils.dismiss()
        }
    }

    private fun initAdapter(users: List<User>) {
        mAdapter = UserAdapter(users, this)
        binding.listUserRecycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@UserActivity, RecyclerView.VERTICAL, false)
        }
        if(users.isNotEmpty()) dialogUtils.dismiss()
    }

    override fun onItemClick(user: User) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra("user", Gson().toJson(userViewModel.users.value?.find { it.id == user.id }))
        launcher.launch(intent)
    }
}