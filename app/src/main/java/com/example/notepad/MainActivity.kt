package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.notepad.data.PageDatabase
import com.example.notepad.domain.Page
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val db = Room.databaseBuilder(this, PageDatabase::class.java, "PageListDataBase.db")
            .build()
        val page: Page = Page(0,"hello")
        val page2: Page = Page(0,"hello")


        GlobalScope.launch {

            val data = db.pageDao().getAllPages()
            for(page in data) {
                val id = page.id
                val a = db.pageDao().findById(id)
                db.pageDao().deletePage(a)
                Log.d("Zalupa", "$id")
            }

        }
    }
}