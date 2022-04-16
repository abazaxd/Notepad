package com.example.notepad.domain

import androidx.lifecycle.LiveData

interface PagesRepository {

    fun addPage(page: Page)

    fun deletePage(page: Page)

    fun editPage(page: Page)

    fun getPage(idPage: Int): Page

    fun getPageList(): LiveData<List<Page>>


}