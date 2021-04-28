package com.allcass.checkboxnotes.view

import com.allcass.checkboxnotes.adapters.CheckboxAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity(), TextView.OnEditorActionListener {
    private lateinit var mViewModel: NoteViewModel
    private lateinit var mAdapter: CheckboxAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        setContentView(R.layout.activity_note)
        setupRecycler()
        setListeners()
    }
    fun setupRecycler(){
        mAdapter = CheckboxAdapter(mutableListOf())
        recycler_checkbox.adapter = mAdapter
        recycler_checkbox.layoutManager = LinearLayoutManager(this)
            .apply { orientation = LinearLayoutManager.VERTICAL }
    }
    fun setListeners(){
        edit_checkbox.setOnEditorActionListener(this)
    }
    override fun onEditorAction(editView: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if(editView.id == R.id.edit_checkbox){
            mViewModel.createCheckbox(editView,mAdapter)
            return true
        }
        return false
    }

}