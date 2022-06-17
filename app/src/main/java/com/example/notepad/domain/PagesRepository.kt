package com.example.notepad.domain

import androidx.lifecycle.LiveData

interface PagesRepository {

    suspend fun addPage(page: Page)

    suspend fun deletePage(page: Page)

    suspend fun editPage(page: Page)

    suspend fun getPage(idPage: Int): Page

    fun getPageList(): LiveData<List<Page>>


}