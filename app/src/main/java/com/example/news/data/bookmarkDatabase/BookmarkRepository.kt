package com.example.news.data.bookmarkDatabase

import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun getAllBookmarks(): Flow<List<BookmarkEntity>>
    suspend fun addBookmark(bookmark: BookmarkEntity)
    suspend fun deleteBookmarkById(title: String)
}

class OfflineBookmarkRepository(private val bookmarkDao: BookmarkDao): BookmarkRepository {

    override suspend fun getAllBookmarks(): Flow<List<BookmarkEntity>> = bookmarkDao.getAllItems()

    override suspend fun addBookmark(bookmark: BookmarkEntity) = bookmarkDao.insert(bookmark)

    override suspend fun deleteBookmarkById(title: String) = bookmarkDao.delete(title)
}

