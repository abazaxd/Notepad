package com.example.notepad.domain

import androidx.lifecycle.LiveData

class GetPageListUseCase(private val pagesRepository: PagesRepository) {

    fun getPageList(): LiveData<List<Page>>{
        return pagesRepository.getPageList()
    }
}