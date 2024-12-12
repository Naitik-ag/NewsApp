package com.example.news.data.bookmarkDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookmarkEntity: BookmarkEntity)

    @Query("DELETE FROM bookmark_table WHERE title = :title")
    suspend fun delete(title: String)

    @Query("SELECT * from bookmark_table ORDER BY id DESC")
    fun getAllItems(): Flow<List<BookmarkEntity>>
}