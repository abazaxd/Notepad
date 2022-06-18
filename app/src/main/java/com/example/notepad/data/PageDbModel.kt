package com.example.notepad.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pages")
data class PageDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String
)