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

class CheckBoxViewModel(application: Application) : AndroidViewModel(application) {


    private val mContext = application.applicationContext
    private val mCheckBoxRepository: CheckBoxRepository = CheckBoxRepository(mContext)
    //lista dos checkBoxes deletados
    private val mCheckBoxDeletedList = mutableListOf<CheckBoxModel>()

    private val mCheckBoxList = MutableLiveData<List<CheckBoxModel>>()
    val checkBoxList: LiveData<List<CheckBoxModel>> = mCheckBoxList

    private val mNote = MutableLiveData<NoteModel>()
    val title: LiveData<NoteModel> = mNote



    fun save(id:Long, adapter: CheckboxAdapter, titleNote: String) {

        var noteId = id

        //seta o título da nossa nota na NoteModel
        val noteModel = NoteModel().apply {
            this.title = titleNote
        }
        if (id == 0.toLong()){
            noteId = mCheckBoxRepository.saveNote(noteModel)
        }

        //recupera o id da nota
        var checklist = adapter.getCheckboxList()
        checklist.forEach {
            it.noteId = noteId
        }

        val listaNovosCheckBoxes = checklist.filter{
            it.id == 0
        }

        if (id == 0.toLong()){
            //passa os dados do nosso adapter/RecyclerView - a checklist criada - para serem salvos no banco
            mCheckBoxRepository.saveCheckBox(checklist)
        } else{
            mCheckBoxRepository.saveCheckBox(listaNovosCheckBoxes)
            mCheckBoxRepository.update(noteModel, mCheckBoxList.value)
        }

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
        checkboxModel: CheckBoxModel, newStatus: Boolean
    ) {
        checkboxModel.status = newStatus
    }

    //cria a lista de checkboxes deletados e deleta do adapter/RecyclerView
    fun onClickImageButton(checkBox: CheckBoxModel, adapter: CheckboxAdapter){
        //a lista de deletados deve pegar apenas os checkboxes que já estão presentes no banco
        var id = checkBox.id
        if(id!=0){
            mCheckBoxDeletedList.add(checkBox)
        }
        adapter.deleteCheckBox(checkBox)
    }
}
