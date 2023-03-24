package com.jorgealdana.ceibaapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jorgealdana.ceibaapp.database.AppDB
import com.jorgealdana.ceibaapp.database.dao.PostDao
import com.jorgealdana.ceibaapp.features.posts.repository.PostRepository
import com.jorgealdana.ceibaapp.features.users.repository.UsersRepository
import com.jorgealdana.ceibaapp.models.Post
import com.jorgealdana.ceibaapp.models.User
import com.jorgealdana.ceibaapp.network.ApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Suite
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@Suite.SuiteClasses(ExampleUnitTest::class)
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test my suspend function`() = runBlocking {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val postDao = object : PostDao {
            override fun getPosts(): List<Post> {
                return emptyList()
            }

            override suspend fun insert(post: Post) {
                //do nothing
            }
        }
        val postRepository = PostRepository(postDao, apiService)
        val scope = CoroutineScope(StandardTestDispatcher())
        val result = scope.async {
            postRepository.getPosts(1)
        }
        val posts = result.await()
        assertEquals(posts.isNotEmpty(), true)
    }

    //create a test function to store a user in the database
    @ExperimentalCoroutinesApi
    @Test
    fun `test my suspend function to insert a user`() = runTest {
        val db =
            AppDB.getDatabase(App().applicationContext)
        val testUser = User(1, "name", "username", "email", "phone")
        db.userDao().insert(testUser)
        val user = db.userDao().getUserById(1)
        assertEquals(user, testUser)
    }

    //create unit test fot the user repository
    @ExperimentalCoroutinesApi
    @Test
    fun `test my suspend function to get users`() = runTest {
        val db =
            AppDB.getDatabase(App().applicationContext)
        val userDao = db.userDao()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val userRepository = UsersRepository(userDao, apiService)
        val users = userRepository.getUsers()
        assertEquals(users.isNotEmpty(), true)
    }
}