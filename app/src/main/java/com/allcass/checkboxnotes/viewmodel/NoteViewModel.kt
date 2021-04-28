package com.allcass.checkboxnotes.viewmodel

import android.app.Application
import com.allcass.checkboxnotes.adapters.CheckboxAdapter
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.service.model.NoteModel
import com.allcass.checkboxnotes.service.repository.CheckBoxRepository

class NoteViewModel(application: Application) : AndroidViewModel(application){

    private val mContext = application.applicationContext
    private val mCheckBoxRepository: CheckBoxRepository = CheckBoxRepository(mContext)

    private var mNote = MutableLiveData<NoteModel>()
    val note: LiveData<NoteModel> = mNote


    fun save(idNote: Int, title: String,idCheckBox: Int, lista ) {
        val note = NoteModel().apply {
            this.id = idNote
            this.title = title
        }
        if (idNote == 0) {
            mCheckBoxRepository.saveNote(note)
        }/*else {

        }*/

        //for pra cada posição da lista
        val checkBox = CheckBoxModel().apply{
            this.id = idCheckBox
            this.noteId = idNote
            this.text = lista.first
            this.status = lista.second
        }
        if (idCheckBox == 0) {
            mCheckBoxRepository.saveCheckBox(checkBox)
        }/*else {

        }*/



    }

    fun createCheckbox(editText: TextView, adapter: CheckboxAdapter){
        val text = editText.text.toString()
        val checked = false
        val position = adapter.checkboxList.size
        adapter.checkboxList.add(position,Pair(checked,text))
        adapter.notifyItemInserted(position)
        editText.text = ""

    }
}
