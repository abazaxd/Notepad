package com.example.notepad.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notepad.data.PagesRepositoryImpl
import com.example.notepad.domain.AddPageUseCase
import com.example.notepad.domain.DeletePageUseCase
import com.example.notepad.domain.GetPageListUseCase
import com.example.notepad.domain.Page

class MainViewModel: ViewModel() {

    val repository = PagesRepositoryImpl

    private val getPageListUseCase = GetPageListUseCase(repository)
    private val deletePageUseCase = DeletePageUseCase(repository)

    val pageList = getPageListUseCase.getPageList()

    fun deletePage(page: Page){

        deletePageUseCase.deletePage(page)
    }

}