package com.example.tugasretrofit_pertemuan11

import PostCodeAdapter
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasretrofit_pertemuan11.databinding.ActivityMainBinding
import com.example.tugasretrofit_pertemuan11.model.PostCodes
import com.example.tugasretrofit_pertemuan11.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchPostCodes()
    }

    private fun fetchPostCodes() {
        ApiClient.getInstance().getAllPostCode().enqueue(object : Callback<List<PostCodes>> {
            override fun onResponse(call: Call<List<PostCodes>>, response: Response<List<PostCodes>>) {
                if (response.isSuccessful) {
                    response.body()?.let { postCodes ->
                        binding.rvRetrofit.apply {
                            layoutManager = GridLayoutManager(this@MainActivity,2)
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