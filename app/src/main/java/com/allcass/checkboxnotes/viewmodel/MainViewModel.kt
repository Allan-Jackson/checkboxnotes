package com.allcass.checkboxnotes.viewmodel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.allcass.checkboxnotes.service.model.NoteModel
import com.allcass.checkboxnotes.service.repository.CheckBoxRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mCheckBoxRepository = CheckBoxRepository(application.applicationContext)

    private val mNoteList = MutableLiveData<List<NoteModel>>()
    val noteList: LiveData<List<NoteModel>> = mNoteList

    fun load(){
        mNoteList.value = mCheckBoxRepository.getAll()
    }
    fun delete(id: Int) {
        val note = mCheckBoxRepository.getNote(id)
        mCheckBoxRepository.deleteAll(note,id)
    }

}