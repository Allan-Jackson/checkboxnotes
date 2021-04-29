package com.allcass.checkboxnotes.viewmodel

import android.app.Application
import android.widget.CompoundButton
import com.allcass.checkboxnotes.view.adapters.CheckboxAdapter
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.service.model.NoteModel
import com.allcass.checkboxnotes.service.repository.CheckBoxRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    //todo: atualizar a lista de Checkbox do adapter para alterar o status de checked depois do usuário ter editado a nota

    private val mContext = application.applicationContext
    private val mCheckBoxRepository: CheckBoxRepository = CheckBoxRepository(mContext)

    private val mCheckBoxList = MutableLiveData<List<CheckBoxModel>>()
    val checkBoxList: LiveData<List<CheckBoxModel>> = mCheckBoxList

    private val mNote = MutableLiveData<NoteModel>()
    val title: LiveData<NoteModel> = mNote

    fun save(adapter: CheckboxAdapter, titleNote: String) {

        //seta o título da nossa nota na NoteModel
        val noteModel = NoteModel().apply {
            this.title = titleNote
        }
        //recupera o id da nota
        var noteId = mCheckBoxRepository.saveNote(noteModel)
        var checklist = adapter.getCheckboxList()
        checklist.forEach {
            it.noteId = noteId
        }

        //passa os dados do nosso adapter/RecyclerView - a checklist criada - para serem salvos no banco
        mCheckBoxRepository.saveCheckBox(checklist)
    }

    //cria um novo CheckBox no RecyclerView
    fun createCheckbox(editText: TextView, adapter: CheckboxAdapter) {
        val text = editText.text.toString()
        val checked = false

        //cria um CheckBox com as configurações e insere na lista de dados do adapter
        adapter.insertCheckbox(
            CheckBoxModel().apply {
                this.text = text
                this.status = checked
            })
    }
    fun loadData(noteId: Long){
        mCheckBoxList.value = mCheckBoxRepository.getCheckBoxesFromNote(noteId)
        mNote.value = mCheckBoxRepository.getNote(noteId)
    }

    //muda o status do CheckBox quando ele é clicado
    fun onCheckChanged(
        checkboxModel: CheckBoxModel, checkboxView: CompoundButton, newStatus: Boolean
    ) {
        checkboxModel.status = newStatus
    }
}
