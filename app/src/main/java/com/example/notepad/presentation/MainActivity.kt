package com.example.notepad.presentation

import android.media.MediaRouter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
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
        viewModel.pageList.observe(this) {
            pageListAdapter.submitList(it)
        }

    }

    private fun setupRecyclerView() {

        val rvPageList = findViewById<RecyclerView>(R.id.rvPageList)
        pageListAdapter = PageListAdapter()
        rvPageList.adapter = pageListAdapter

        setupClickListener()

        setupOnLongClickListener()

        setupSwipeListener(rvPageList)
    }

    private fun setupSwipeListener(rvPageList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val page = pageListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deletePage(page)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvPageList)
    }

    private fun setupClickListener() {
        pageListAdapter.onPageClickListener = {
            Log.d("MSG", "${it.toString()}")
        }
    }

    private fun setupOnLongClickListener() {
        pageListAdapter.onPageLongClickListener = {
            Log.d("long", "${it.toString()}")
        }
    }
}


