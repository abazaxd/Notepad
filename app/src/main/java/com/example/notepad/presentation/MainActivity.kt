package com.example.notepad.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var pageListAdapter: PageListAdapter

    private var pageContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pageContainer = findViewById(R.id.page_container)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.pageList.observe(this) {
            pageListAdapter.submitList(it)
        }

        val buttonAddPage = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        buttonAddPage.setOnClickListener {
            if (isOnePaneOn()) {
                val intent = PageActivity.newIntentAddPage(this)
                startActivity(intent)
            } else {
                launchFragment(PageFragment.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneOn(): Boolean {
        return pageContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.page_container, fragment)
            .addToBackStack(null)
            .commit()
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
            if (isOnePaneOn()) {
                val intent = PageActivity.newIntentEditPage(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(PageFragment.newInstanceEditItem(it.id))
            }
        }
    }
        private fun setupOnLongClickListener() {
            pageListAdapter.onPageLongClickListener = {
                Log.d("long", "${it.toString()}")
            }
        }
    }




