package com.example.notepad.domain

class DeletePageUseCase(private val pagesRepository: PagesRepository) {

    fun deletePage(page: Page){
        pagesRepository.deletePage(page)
    }
}