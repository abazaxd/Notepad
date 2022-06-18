package com.example.notepad.data


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.notepad.domain.Page
import com.example.notepad.domain.Page.Companion.DEFAULT_ID
import com.example.notepad.domain.PagesRepository
import java.lang.RuntimeException

class PagesRepositoryImpl(application: Application) : PagesRepository {

    private val pageDao = AppDataBase.getInstance(application).pageDao()
    private val mapper = Mapper()

    override suspend fun addPage(page: Page) {
        pageDao.addPage(mapper.mapEntityToDbModel(page))
    }

    override suspend fun deletePage(page: Page) {
        pageDao.deletePage(page.id)
    }

    override suspend fun editPage(page: Page) {
        pageDao.addPage(mapper.mapEntityToDbModel(page))
    }

    override suspend fun getPage(idPage: Int): Page {
        val dbModel = pageDao.getPage(idPage)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getPageList(): LiveData<List<Page>> {
        return MediatorLiveData<List<Page>>().apply {
            addSource(pageDao.getPageList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }
    }
}