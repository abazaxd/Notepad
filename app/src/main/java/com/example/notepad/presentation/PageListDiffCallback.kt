package com.example.notepad.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.notepad.domain.Page

class PageListDiffCallback (
    private val oldList: List<Page>,
    private val newList: List<Page>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val oldItemId = oldList[oldItemPosition].id
        val newItemId = newList[newItemPosition].id
        return oldItemId == newItemId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldText = oldList[oldItemPosition]
        val newText = newList[newItemPosition]
        return oldText == newText
    }


}