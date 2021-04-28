package com.allcass.checkboxnotes.view

import android.content.Intent
import com.allcass.checkboxnotes.view.adapters.CheckboxAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity(), TextView.OnEditorActionListener, View.OnClickListener {

    private lateinit var mViewModel: NoteViewModel
    private val mAdapter: CheckboxAdapter = CheckboxAdapter()
    private var mNoteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        setupRecycler()

        loadData()

        setListeners()


    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mNoteId = bundle.getInt("NoteId")
        }
    }

    private fun setupRecycler(){
        recycler_checkbox.adapter = mAdapter
        recycler_checkbox.layoutManager = LinearLayoutManager(this)
            .apply { orientation = LinearLayoutManager.VERTICAL }
    }

    private fun setListeners(){
        edit_checkbox.setOnEditorActionListener(this)
        buttonSave.setOnClickListener(this)
    }

    override fun onEditorAction(editView: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if(editView.id == R.id.edit_checkbox){
            mViewModel.createCheckbox(editView,mAdapter)
            return true
        }
        return false
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonSave) {
            val text = titleNote.text.toString()

            mViewModel.save(mAdapter, text)

            startActivity(Intent(this,MainActivity::class.java))
        }
    }




}