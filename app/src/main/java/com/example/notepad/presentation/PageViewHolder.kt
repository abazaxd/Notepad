package com.example.notepad.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.R

class PageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvPage = view.findViewById<TextView>(R.id.tvPage)
}