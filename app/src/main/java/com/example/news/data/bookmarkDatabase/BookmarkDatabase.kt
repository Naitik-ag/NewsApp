package com.example.news.data.bookmarkDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookmarkEntity::class], version = 6, exportSchema = false)
abstract class BookmarkDatabase: RoomDatabase(){
    abstract fun bookmarkDao(): BookmarkDao
    companion object{

        @Volatile
        private var Instance: BookmarkDatabase? = null

        fun getDatabase(context: Context): BookmarkDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, BookmarkDatabase::class.java, "bookmark_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }

        }

    }
}