package com.example.notepad.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.example.notepad.domain.Page

class PageListAdapter: RecyclerView.Adapter<PageListAdapter.pageViewHolder>() {

    val list = listOf<Page>()

    class pageViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tvText = view.findViewById<TextView>(R.id.tvPage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pageViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.page, parent, false)
        return pageViewHolder(view)
    }

    override fun onBindViewHolder(holder: pageViewHolder, position: Int) {

        val page = list[position]
        holder.tvText.text = page.text
    }

    override fun getItemCount(): Int {

        return list.size
    }
}