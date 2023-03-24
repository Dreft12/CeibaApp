package com.jorgealdana.ceibaapp.features.posts

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jorgealdana.ceibaapp.App
import com.jorgealdana.ceibaapp.databinding.ActivityPostBinding
import com.jorgealdana.ceibaapp.features.posts.adapters.PostAdapter
import com.jorgealdana.ceibaapp.features.posts.viewModel.PostViewModel
import com.jorgealdana.ceibaapp.features.posts.viewModel.PostViewModelFactory
import com.jorgealdana.ceibaapp.models.Post
import com.jorgealdana.ceibaapp.models.User

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var mAdapter: PostAdapter

    private val postViewModel: PostViewModel by viewModels {
        PostViewModelFactory((application as App).postRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
        setUpTitles()
        initAdapter()
    }

    private fun initAdapter() {
        postViewModel.posts.observe(this) {
            mAdapter = PostAdapter(it)
            binding.listPosts.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(this@PostActivity, RecyclerView.VERTICAL, false)
            }
        }
    }
    private fun setUpTitles() {
        val user = Gson().fromJson(intent.getStringExtra("user"), User::class.java)
        postViewModel.loadPostsByUser(user.id ?: 0)
        binding.nameTxt.text = user.name
        binding.emailPostTxt.text = user.email
        binding.phoneTxt.text = user.phone
    }

    private fun setUpToolbar() {
        setSupportActionBar((binding.toolbar as Toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}