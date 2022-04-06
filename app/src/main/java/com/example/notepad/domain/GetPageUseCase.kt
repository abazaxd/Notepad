package com.example.notepad.domain

class GetPageUseCase(private val pagesRepository: PagesRepository) {

    fun getPage(idPage: Int): Page{
        return pagesRepository.getPage(idPage)
    }
}