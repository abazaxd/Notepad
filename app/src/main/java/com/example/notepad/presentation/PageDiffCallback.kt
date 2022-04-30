package com.example.notepad.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.notepad.domain.Page

class PageDiffCallback: DiffUtil.ItemCallback<Page>() {
    override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {

        return oldItem == newItem
    }


}