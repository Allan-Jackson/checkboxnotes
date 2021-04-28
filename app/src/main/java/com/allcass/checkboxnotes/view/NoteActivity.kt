package com.allcass.checkboxnotes.view

import android.content.Intent
import com.allcass.checkboxnotes.view.adapters.CheckboxAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.view.listener.CheckBoxListener
import com.allcass.checkboxnotes.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity(), TextView.OnEditorActionListener, View.OnClickListener,
    CheckBoxListener {

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
        setObservers()



    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mNoteId = bundle.getInt("NoteId")
        }
    }

    private fun setupRecycler() {
        recycler_checkbox.adapter = mAdapter
        recycler_checkbox.layoutManager = LinearLayoutManager(this)
            .apply { orientation = LinearLayoutManager.VERTICAL }
    }

    private fun setListeners() {
        edit_checkbox.setOnEditorActionListener(this)
        buttonSave.setOnClickListener(this)
        mAdapter.attachListener(this)
    }

    //observa a lista de checkboxes e a atualiza quando ela for alterada na ViewModel
    private fun setObservers(){
        mViewModel.checkBoxList.observe(this,{
            mAdapter.updateCheckBoxList(it) //atualiza a lista de checkboxes do adapter/recyclerView
        })
    }

    override fun onEditorAction(editView: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if (editView.id == R.id.edit_checkbox) {
            mViewModel.createCheckbox(editView, mAdapter)
            editView.text = ""
            return true
        }
        return false
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.buttonSave -> {
                val txtTitleNote = titleNote.text.toString()
                mViewModel.save(mAdapter, txtTitleNote)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    //evento de mudança do checkbox
    override fun onCheckChanged(
        checkboxModel: CheckBoxModel, checkboxView: CompoundButton, newStatus: Boolean
    ) {
        mViewModel.onCheckChanged(checkboxModel, checkboxView, newStatus)
    }



}