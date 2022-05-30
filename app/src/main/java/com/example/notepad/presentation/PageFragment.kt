package com.example.notepad.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.service.controls.templates.TemperatureControlTemplate.MODE_UNKNOWN
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notepad.R
import com.example.notepad.domain.Page

class PageFragment : Fragment() {

    private lateinit var viewModel: PageViewModel

    private lateinit var etText: EditText
    private lateinit var btSave: Button

    private var screenMode: String = MODE_UNKNOWN
    private var pageId: Int = Page.DEFAULT_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PageViewModel::class.java]
        initViews(view)
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchADDMode()
        }

        viewModel.finishActivity.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }

    }

    private fun launchEditMode() {
        viewModel.getPage(pageId)
        viewModel.page.observe(viewLifecycleOwner) {
            etText.setText(it.text)
        }
        btSave.setOnClickListener {
            viewModel.editPage(etText.text?.toString())
        }
    }

    private fun launchADDMode() {
        btSave.setOnClickListener {
            viewModel.addPage(etText.text?.toString())
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)){
            throw RuntimeException("Param screen mode is absent.")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD){
            throw RuntimeException("Unknown screen mode.")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT){
            if (!args.containsKey(PAGE_ID)) {
                throw RuntimeException("Param Page Id is absent.")
            }
            pageId = args.getInt(PAGE_ID, Page.DEFAULT_ID)
        }
    }

    private fun initViews(view: View) {

        etText = view.findViewById(R.id.etText)
        btSave = view.findViewById(R.id.btSave)
    }

    companion object {

        private const val SCREEN_MODE = "extra_mode"
        private const val PAGE_ID = "extra_page_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): PageFragment {
            return PageFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(pageId: Int): PageFragment {
            return PageFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(PAGE_ID, pageId)
                }
            }
        }
    }
}
