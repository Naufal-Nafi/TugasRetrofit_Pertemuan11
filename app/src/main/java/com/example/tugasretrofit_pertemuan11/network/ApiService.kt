package com.example.tugasretrofit_pertemuan11.network

import com.example.tugasretrofit_pertemuan11.model.PostCodes
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("kota_kab/k22.json")
    fun getAllPostCode(): Call<List<PostCodes>>
}