package com.example.notepad.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Page(
    @PrimaryKey(autoGenerate = true) var id : Int,
    var text: String
)
