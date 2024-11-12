package com.example.tugasretrofit_pertemuan11.model

import com.google.gson.annotations.SerializedName

data class PostCodes(
    @SerializedName("kecamatan")
    val kecamatan: String,
    @SerializedName("kelurahan")
    val kelurahan: String,
    @SerializedName("kodepos")
    val kodepos: String
)
