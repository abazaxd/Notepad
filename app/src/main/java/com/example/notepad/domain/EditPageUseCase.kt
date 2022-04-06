package com.example.notepad.domain

class EditPageUseCase(private val pagesRepository: PagesRepository) {

    fun editPage(page: Page){
        pagesRepository.editPage(page)
    }
}