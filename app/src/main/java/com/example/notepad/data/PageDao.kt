package com.example.notepad.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notepad.domain.Page

@Dao
interface PageDao {
    @Query("SELECT * FROM Page")
    fun getAllPages(): List<Page>

    @Query("SELECT * FROM Page WHERE id LIKE :pageId")
    fun findById(pageId: Int): Page

    @Query("SELECT id FROM Page ")
    fun getPageId(): Int


    @Insert
    fun addPage(page: Page)

    @Delete
    fun deletePage(page: Page)
}