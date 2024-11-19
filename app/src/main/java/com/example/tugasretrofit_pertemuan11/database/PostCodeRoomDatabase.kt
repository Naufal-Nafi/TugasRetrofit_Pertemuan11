package com.example.tugasretrofit_pertemuan11.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [PostCode::class], version = 1, exportSchema = false)
abstract class PostCodeRoomDatabase :RoomDatabase() {
    abstract fun postCodeDao(): PostCodeDao ?
    companion object {
        @Volatile
        private var INSTANCE: PostCodeRoomDatabase? = null
        fun getDatabase(context: Context): PostCodeRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(PostCodeRoomDatabase::class.java) {
                    INSTANCE = databaseBuilder(
                        context.applicationContext,
                        PostCodeRoomDatabase::class.java, "postcode_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}