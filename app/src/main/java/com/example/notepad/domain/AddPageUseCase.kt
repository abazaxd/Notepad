package com.example.notepad.domain

class AddPageUseCase(private val pagesRepository: PagesRepository) {

    fun addPage(page: Page){
        pagesRepository.addPage(page)
    }
}