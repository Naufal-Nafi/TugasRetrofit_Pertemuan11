package com.example.tugasretrofit_pertemuan11

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugasretrofit_pertemuan11.database.PostCode
import com.example.tugasretrofit_pertemuan11.database.PostCodeDao
import com.example.tugasretrofit_pertemuan11.database.PostCodeRoomDatabase
import com.example.tugasretrofit_pertemuan11.databinding.ActivityFavoriteBinding
import com.example.tugasretrofit_pertemuan11.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var mPostCodeDao: PostCodeDao
    private lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        executorService = Executors.newSingleThreadExecutor()
        val db = PostCodeRoomDatabase.getDatabase(binding.root.context)
        mPostCodeDao = db!!.postCodeDao()!!

    }



    fun getAllNotes() {
        mPostCodeDao.allPostCodes.observe(this) {
                notes ->
            val adapter : ArrayAdapter<PostCode> =
                ArrayAdapter<PostCode>(this, android.R.layout.simple_list_item_1,notes)

            binding.listView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }
}