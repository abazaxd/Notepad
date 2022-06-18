package com.example.notepad.domain

class DeletePageUseCase(private val pagesRepository: PagesRepository) {

    suspend fun deletePage(page: Page){
        pagesRepository.deletePage(page)
    }
}