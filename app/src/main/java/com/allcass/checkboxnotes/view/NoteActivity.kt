package com.allcass.checkboxnotes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.view.adapters.NoteAdapter
import com.allcass.checkboxnotes.view.listener.NoteListener
import com.allcass.checkboxnotes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*


class NoteActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: NoteViewModel
    private val mAdapter: NoteAdapter = NoteAdapter()
    private lateinit var mListener: NoteListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        setupRecycler()

        mViewModel.load()

        setListeners()

        setObservers()
    }

    private fun setupRecycler(){
        recycler_main.adapter = mAdapter
        recycler_main.layoutManager = LinearLayoutManager(this)
            .apply { orientation = LinearLayoutManager.VERTICAL }
    }



    private fun setListeners(){
        mListener = object : NoteListener {
            override fun onClick(id: Long) {
                val intent = Intent(this@NoteActivity, CheckBoxActivity::class.java)
                val bundle = Bundle()
                bundle.putLong("NoteId", id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Long) {
                mViewModel.delete(id)
                mViewModel.load()
            }
        }

        mAdapter.attachListener(mListener)
        button_floating.setOnClickListener(this)
    }

    private fun setObservers() {
        mViewModel.noteList.observe(this, Observer {
            mAdapter.updateNotes(it)
        })
    }

    override fun onClick(view: View) {
        val id = view.id

        if(id == R.id.button_floating){
            startActivity(Intent(this,CheckBoxActivity::class.java))
        }
    }

}
