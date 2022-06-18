package com.example.notepad.domain

class EditPageUseCase(private val pagesRepository: PagesRepository) {

    suspend fun editPage(page: Page){
        pagesRepository.editPage(page)
    }
}