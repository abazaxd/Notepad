package com.example.notepad.domain

class AddPageUseCase(private val pagesRepository: PagesRepository) {

    suspend fun addPage(page: Page){
        pagesRepository.addPage(page)
    }
}