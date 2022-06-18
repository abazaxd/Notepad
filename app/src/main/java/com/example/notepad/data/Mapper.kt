package com.example.notepad.data

import com.example.notepad.domain.Page

class Mapper {

    fun mapEntityToDbModel(page: Page): PageDbModel{
        return PageDbModel(
            id = page.id,
            text = page.text
        )
    }

    fun mapDbModelToEntity(pageDbModel: PageDbModel): Page{
        return Page(
            id = pageDbModel.id,
            text = pageDbModel.text
        )
    }

    fun mapListDbModelToListEntity(list: List<PageDbModel>): List<Page>{
        return list.map {
            mapDbModelToEntity(it)
        }
    }
}