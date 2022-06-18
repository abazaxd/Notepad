package com.example.notepad.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notepad.data.PagesRepositoryImpl
import com.example.notepad.domain.AddPageUseCase
import com.example.notepad.domain.EditPageUseCase
import com.example.notepad.domain.GetPageUseCase
import com.example.notepad.domain.Page
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PageViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PagesRepositoryImpl(application)

    private val addPageUseCase = AddPageUseCase(repository)
    private val editPageUseCase = EditPageUseCase(repository)
    private val getPageUseCase = GetPageUseCase(repository)

    private val _finishActivity = MutableLiveData<Unit>()
    val finishActivity: LiveData<Unit>
        get() = _finishActivity

    fun addPage(inputText: String?) {
        viewModelScope.launch {
            val text = parseText(inputText)
            if (validateInput(text)) {
                val page = Page(text)
                addPageUseCase.addPage(page)
                finishWork()
            }
        }

    }

    fun editPage(inputText: String?) {
        viewModelScope.launch {
            val text = parseText(inputText)
            if (validateInput(text)) {
                _page.value?.let {
                    val page = it.copy(text = text)
                    editPageUseCase.editPage(page)
                    finishWork()
                }
            }
        }
    }

    private val _page = MutableLiveData<Page>()
    val page: LiveData<Page>
        get() = _page

    fun getPage(id: Int) {
        viewModelScope.launch {
            val page = getPageUseCase.getPage(id)
            _page.value = page
        }
    }

    private fun parseText(inputText: String?): String {

        return inputText?.trim() ?: ""
    }

    private fun validateInput(text: String): Boolean {

        return text.isNotBlank()
    }


    private fun finishWork() {
        _finishActivity.value = Unit
    }
}