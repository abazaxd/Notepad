package com.example.notepad.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notepad.domain.Page
import com.example.notepad.domain.Page.Companion.DEFAULT_ID
import com.example.notepad.domain.PagesRepository
import java.lang.RuntimeException

object PagesRepositoryImpl: PagesRepository {

    private val pageListLiveData = MutableLiveData<List<Page>>()
    private val pageList = sortedSetOf(Comparator<Page> { p0, p1 -> p0.id.compareTo(p1.id) })

    private var autoId = 0

    init {
        for (i in 0 until 30){
            val page = Page("hello")
            addPage(page)
        }
    }

    override fun addPage(page: Page) {

        if (page.id == DEFAULT_ID) {
            page.id = autoId
            autoId++
            pageList.add(page)
        }
        pageList.add(page)
        updateList()
    }

    override fun deletePage(page: Page) {
        pageList.remove(page)
        updateList()
    }

    override fun editPage(page: Page) {
        val oldElement = getPage(page.id)
        pageList.remove(oldElement)
        addPage(page)
    }

    override fun getPage(idPage: Int): Page {
        return pageList.find { it.id == idPage
        } ?: throw RuntimeException("Page with id: $idPage not found.")
    }

    override fun getPageList(): LiveData<List<Page>> {
        return pageListLiveData
    }

    private fun updateList(){
        pageListLiveData.value = pageList.toList()
    }
}