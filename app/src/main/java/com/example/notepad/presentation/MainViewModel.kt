package com.example.notepad.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.notepad.data.PagesRepositoryImpl
import com.example.notepad.domain.AddPageUseCase
import com.example.notepad.domain.DeletePageUseCase
import com.example.notepad.domain.GetPageListUseCase
import com.example.notepad.domain.Page
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    val repository = PagesRepositoryImpl(application)

    private val getPageListUseCase = GetPageListUseCase(repository)
    private val deletePageUseCase = DeletePageUseCase(repository)

    val pageList = getPageListUseCase.getPageList()

    fun deletePage(page: Page){
        viewModelScope.launch {
            deletePageUseCase.deletePage(page)
        }
    }
}