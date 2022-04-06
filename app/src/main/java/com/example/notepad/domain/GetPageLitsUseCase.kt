package com.example.notepad.domain

class GetPageListUseCase(private val pagesRepository: PagesRepository) {

    fun getPageList(): List<Page>{
        return pagesRepository.getPageList()
    }
}