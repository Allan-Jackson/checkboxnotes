package com.allcass.checkboxnotes.viewmodel

import android.app.Application
import android.provider.ContactsContract
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

    //todo: atualizar a lista de Checkbox do adapter para alterar o status de checked depois do usuário ter editado a nota

    private val mContext = application.applicationContext
    private val mCheckBoxRepository: CheckBoxRepository = CheckBoxRepository(mContext)

    fun save(adapter: CheckboxAdapter, title: String) {
        val noteModel = NoteModel().apply {
            this.title = title
        }

        var noteId = mCheckBoxRepository.saveNote(noteModel)

        val checkboxModelList = mutableListOf<CheckBoxModel>()
        adapter.checkboxList.forEach {
            checkboxModelList.add(
                CheckBoxModel().apply {
                    this.noteId = noteId
                    text = it.second
                    status = it.first
                }
            )
        }
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
