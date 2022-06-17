package com.example.notepad.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PageDao {

    @Query("SELECT * FROM pages")
    fun getPageList(): LiveData<List<PageDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPage(page: PageDbModel)

    @Query("DELETE FROM pages WHERE id=:pageId")
    suspend fun deletePage(pageId: Int)

    @Query("SELECT * FROM pages WHERE id=:pageId LIMIT 1")
    suspend fun getPage(pageId: Int): PageDbModel
}