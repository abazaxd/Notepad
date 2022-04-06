package com.example.notepad.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notepad.domain.Page

@Database(entities = [Page::class], version = 1)
abstract class PageDatabase : RoomDatabase() {
    abstract fun pageDao(): PageDao
}