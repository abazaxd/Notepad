package com.example.notepad.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R
import com.example.notepad.domain.Page

class PageListAdapter : RecyclerView.Adapter<PageListAdapter.pageViewHolder>() {

    var count = 0

    var onPageLongClickListener: ((Page) -> Unit)? = null

    var onPageClickListener : ((Page) -> Unit)? = null

    var pageList = listOf<Page>()
        set(value) {
            val callback = PageListDiffCallback(pageList, value)
            val diffResult  = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    class pageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvPage = view.findViewById<TextView>(R.id.tvPage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pageViewHolder {


        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.page,
            parent,
            false
        )
        return pageViewHolder(view)
    }

    override fun onBindViewHolder(holder: pageViewHolder, position: Int) {

        count++
        Log.d("onBind", "$count")

        val page = pageList[position]
        holder.itemView.setOnClickListener {
            onPageClickListener?.invoke(page)
        }

        holder.tvPage.text = page.text
    }

    override fun getItemCount(): Int {

        return pageList.size
    }
}