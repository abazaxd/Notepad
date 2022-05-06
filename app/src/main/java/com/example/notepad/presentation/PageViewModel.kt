package com.example.notepad.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notepad.data.PagesRepositoryImpl
import com.example.notepad.domain.AddPageUseCase
import com.example.notepad.domain.EditPageUseCase
import com.example.notepad.domain.GetPageUseCase
import com.example.notepad.domain.Page

class PageViewModel: ViewModel() {

    val repository = PagesRepositoryImpl

    private val addPageUseCase = AddPageUseCase(repository)
    private val editPageUseCase = EditPageUseCase(repository)
    private val getPageUseCase = GetPageUseCase(repository)

    private val _errorInputText = MutableLiveData<Boolean>()
    val errorInputText: LiveData<Boolean>
        get() = _errorInputText

    private val _finishActivity = MutableLiveData<Unit>()
    val finishActivity: LiveData<Unit>
        get() = _finishActivity

    fun addPage(inputText: String?){

        val text = parseText(inputText)
        if (validateInput(text)) {
            val page = Page(text)
            addPageUseCase.addPage(page)
            finishWork()
        }
    }

    fun editPage(inputText: String?){

        val text = parseText(inputText)
        if (validateInput(text)) {
            _page.value?.let {
                val page = it.copy(text = text)
                editPageUseCase.editPage(page)
                finishWork()
            }

        }


    }

    private val _page = MutableLiveData<Page>()
    val page: LiveData<Page>
        get() = _page

    fun getPage(id: Int){

        val page = getPageUseCase.getPage(id)
        _page.value = page
    }

    private fun parseText(inputText: String?): String{

        return inputText?.trim() ?: ""
    }

    private fun validateInput(text: String): Boolean{

        return if (text.isNotBlank()){
            true
        } else {
            _errorInputText.value = true
            false
        }
    }

    public fun resetErrorInputText(){

        _errorInputText.value = false
    }

    private fun finishWork(){
        _finishActivity.value = Unit
    }
}