package com.example.tugasretrofit_pertemuan11

import PostCodeAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasretrofit_pertemuan11.database.PostCodeDao
import com.example.tugasretrofit_pertemuan11.database.PostCodeRoomDatabase
import com.example.tugasretrofit_pertemuan11.databinding.ActivityMainBinding
import com.example.tugasretrofit_pertemuan11.model.PostCodes
import com.example.tugasretrofit_pertemuan11.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnToFavorite.setOnClickListener {
                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }


        fetchPostCodes()
    }



    private fun fetchPostCodes() {
        ApiClient.getInstance().getAllPostCode().enqueue(object : Callback<List<PostCodes>> {
            override fun onResponse(call: Call<List<PostCodes>>, response: Response<List<PostCodes>>) {
                if (response.isSuccessful) {
                    response.body()?.let { postCodes ->
                        binding.rvRetrofit.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = PostCodeAdapter(postCodes)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<PostCodes>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi error", Toast.LENGTH_LONG).show()
            }
        })
    }
}