package com.example.tugasretrofit_pertemuan11.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PostCodeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: PostCode)
    @Update()
    fun update(note: PostCode)
    @Delete()
    fun delete(note: PostCode)
    @get:Query("SELECT * from postcode_table ORDER BY id ASC")
    val allPostCodes: LiveData<List<PostCode>>
    @Query("DELETE FROM postcode_table")
    fun nukeTable()
//    @Query("SELECT * FROM postcode_table WHERE kelurahan = :kelurahan LIMIT 1")
//    fun getPostCodeByKelurahan(kelurahan: String): PostCode?
}