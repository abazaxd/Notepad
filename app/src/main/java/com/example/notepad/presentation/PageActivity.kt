package com.example.notepad.presentation

import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notepad.R

class PageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_layout)

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("PageActivity", mode.toString())
    }
    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_PAGE_ID = "extra_page_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        fun newIntentAddPage(context: Context): Intent{
            val intent = Intent(context, PageActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditPage(context: Context, id: Int):Intent{
            val intent = Intent(context, PageActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_PAGE_ID, id)
            return intent
        }
    }
}
