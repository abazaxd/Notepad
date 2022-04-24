package com.example.notepad.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.notepad.R
import com.example.notepad.domain.Page


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var pageListAdapter: PageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.pageList.observe(this){
            pageListAdapter.pageList = it
        }

    }

    private fun setupRecyclerView(){

        val rvPageList = findViewById<RecyclerView>(R.id.rvPageList)
        pageListAdapter = PageListAdapter()
        rvPageList.adapter = pageListAdapter

        pageListAdapter.onPageLongClickListener = {
            viewModel.deletePage(it)
        }

        pageListAdapter.onPageClickListener = {
            Log.d("MSG", "${it.id}")
        }
    }
}

