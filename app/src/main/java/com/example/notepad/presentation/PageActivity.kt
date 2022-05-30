package com.example.notepad.presentation

import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.notepad.R
import com.example.notepad.domain.Page

class PageActivity : AppCompatActivity() {

//    private lateinit var viewModel: PageViewModel
//
//    private lateinit var etText: EditText
//    private lateinit var btSave: Button
//
    private var screenMode = MODE_UNKNOWN
    private var pageId = Page.DEFAULT_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_layout)
        parseIntent()
//        viewModel = ViewModelProvider(this)[PageViewModel::class.java]
//        initViews()
        launchRightMode()
//
//        viewModel.finishActivity.observe(this){
//            finish()
//        }
//
//    }
//
//    private fun launchEditMode(){
//        viewModel.getPage(pageId)
//        viewModel.page.observe(this) {
//            etText.setText(it.text)
//        }
//        btSave.setOnClickListener {
//            viewModel.editPage(etText.text?.toString())
//        }
//    }
//
//    private fun launchADDMode() {
//        btSave.setOnClickListener {
//            viewModel.addPage(etText.text?.toString())
//        }
//    }
//
    }
    private fun parseIntent(){
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("Param screen mode is absent.")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD){
            throw RuntimeException("Unknown screen mode.")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT){
            if (!intent.hasExtra(EXTRA_PAGE_ID)) {
                    throw RuntimeException("Param Page Id is absent.")
                }
            pageId = intent.getIntExtra(EXTRA_PAGE_ID, Page.DEFAULT_ID)
        }
    }

    private fun launchRightMode(){
        val fragment = when (screenMode) {
                MODE_EDIT -> PageFragment.newInstanceEditItem(pageId)
                MODE_ADD -> PageFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode.")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.pageContainer, fragment)
            .commit()
    }
//
//    private fun initViews(){
//
//        etText = findViewById(R.id.etText)
//        btSave = findViewById(R.id.btSave)

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_PAGE_ID = "extra_page_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

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
