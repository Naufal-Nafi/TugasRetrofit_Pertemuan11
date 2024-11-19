package com.example.tugasretrofit_pertemuan11.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postcode_table")
data class PostCode(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    @ColumnInfo(name="kecamatan")
    val kecamatan: String,
    @ColumnInfo(name="kelurahan")
    val kelurahan: String,
    @ColumnInfo(name="kodepos")
    val kodepos: String
)
