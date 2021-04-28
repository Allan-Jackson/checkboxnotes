package com.allcass.checkboxnotes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.adapters.CheckboxAdapter
import com.allcass.checkboxnotes.adapters.NoteAdapter
import com.allcass.checkboxnotes.service.model.NoteModel
import com.allcass.checkboxnotes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAdapter: NoteAdapter
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupRecycler()

        mViewModel.load()

        setListeners()

        observe()
    }

    fun setupRecycler(){
        mAdapter = NoteAdapter()
        recycler_main.adapter = mAdapter
        recycler_main.layoutManager = LinearLayoutManager(this)
            .apply { orientation = LinearLayoutManager.VERTICAL }
    }
    fun setListeners(){
        button_floating.setOnClickListener(this)
    }

    private fun observe() {
        mViewModel.noteList.observe(this, Observer {
            mAdapter.updateNotes(it)
        })
    }

    override fun onClick(view: View) {
        val id = view.id

        if(id == R.id.button_floating){
            startActivity(Intent(this,NoteActivity::class.java))
        }
    }

}
