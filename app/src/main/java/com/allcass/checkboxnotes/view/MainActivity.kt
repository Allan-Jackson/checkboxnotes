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
import com.allcass.checkboxnotes.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: MainViewModel
    private val mAdapter: NoteAdapter = NoteAdapter()
    private lateinit var mListener: NoteListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupRecycler()

        mViewModel.load()

        setListeners()

        observe()

        mListener = object : NoteListener {
            override fun onClick(id: Int) {
                val intent = Intent(this@MainActivity, NoteActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("NoteId", id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load()
            }
        }

        mAdapter.attachListener(mListener)




    }

    private fun setupRecycler(){
        recycler_main.adapter = mAdapter
        recycler_main.layoutManager = LinearLayoutManager(this)
            .apply { orientation = LinearLayoutManager.VERTICAL }
    }



    private fun setListeners(){
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
