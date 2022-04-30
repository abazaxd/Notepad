package com.example.notepad.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.example.notepad.domain.Page

class PageListAdapter : ListAdapter<Page, PageViewHolder>(PageDiffCallback()) {

    var onPageLongClickListener: ((Page) -> Unit)? = null
    var onPageClickListener : ((Page) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.page,
            parent,
            false
        )
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {

        val page = getItem(position)

        holder.itemView.setOnClickListener {
            onPageClickListener?.invoke(page)
        }

        holder.itemView.setOnLongClickListener {
            onPageLongClickListener?.invoke(page)
            true
        }

        holder.tvPage.text = page.text
    }
}