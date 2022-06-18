package com.example.notepad.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Page(

    var text: String,
    var id : Int = DEFAULT_ID
){
    companion object {
       const val DEFAULT_ID = 0
    }
}
