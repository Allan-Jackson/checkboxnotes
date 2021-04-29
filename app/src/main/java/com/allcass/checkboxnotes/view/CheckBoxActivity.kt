package com.allcass.checkboxnotes.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.allcass.checkboxnotes.R
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.view.adapters.CheckboxAdapter
import com.allcass.checkboxnotes.view.listener.CheckBoxListener
import com.allcass.checkboxnotes.viewmodel.CheckBoxViewModel
import kotlinx.android.synthetic.main.activity_note.*

class CheckBoxActivity : AppCompatActivity(), TextView.OnEditorActionListener, View.OnClickListener,
    CheckBoxListener {

    private lateinit var mViewModel: CheckBoxViewModel
    private val mAdapter: CheckboxAdapter = CheckboxAdapter()
    private var mNoteId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        mViewModel = ViewModelProvider(this).get(CheckBoxViewModel::class.java)

        cursor()
        loadData()
        setupRecycler()
        setListeners()
        setObservers()

    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mNoteId = bundle.getLong("NoteId")
            mViewModel.loadData(mNoteId)
        }

    }

    private fun cursor(){
        val bundle = intent.extras
        if (bundle == null){
            titleNote.requestFocus()
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
        mViewModel.checkBoxList.observe(this, {
            mAdapter.updateCheckBoxList(it) //atualiza a lista de checkboxes do adapter/recyclerView
        })
        mViewModel.title.observe(this, {
            titleNote.setText(it.title)
        })
    }

    override fun onEditorAction(editView: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if (editView.id == R.id.edit_checkbox ) {
            if(editView.text.isEmpty()) {
                val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)

            }else{
                mViewModel.createCheckbox(editView, mAdapter)
                editView.text = ""
            }
            return true
        }
        return false
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.buttonSave -> {
                val txtTitleNote = titleNote.text.toString()

                if(!txtTitleNote.isEmpty()) {
                    mViewModel.save(mNoteId, mAdapter, txtTitleNote)
                    startActivity(Intent(this, NoteActivity::class.java))
                }else{
                    Toast.makeText(this, "Insira um título!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //evento de mudança do checkbox
    override fun onCheckChanged(
        checkboxModel: CheckBoxModel, newStatus: Boolean
    ) {
        mViewModel.onCheckChanged(checkboxModel, newStatus)
    }

    override fun onClickImageButton(checkBox: CheckBoxModel) {
        mViewModel.onClickImageButton(checkBox, mAdapter)
    }


}