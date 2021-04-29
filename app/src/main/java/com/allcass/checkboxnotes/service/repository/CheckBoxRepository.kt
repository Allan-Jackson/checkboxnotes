package com.allcass.checkboxnotes.service.repository

import android.content.Context
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.service.model.NoteModel

class CheckBoxRepository(context: Context) {

    // Acesso ao banco de dados
    private val mCheckBoxDataBase = CheckBoxDataBase.getDatabase(context).checkBoxDAO()
    private val mNoteDataBase = CheckBoxDataBase.getDatabase(context).noteDAO()

    fun saveNote(note: NoteModel): Long {
        return mNoteDataBase.save(note)
    }

    fun update(note: NoteModel, checkBox: List<CheckBoxModel>?){
        mCheckBoxDataBase.update(checkBox)
        mNoteDataBase.update(note)
    }

    fun saveCheckBox(checkboxList: List<CheckBoxModel>) {
        checkboxList.forEach {
            mCheckBoxDataBase.save(it)
        }
    }

    fun getAll(): List<NoteModel> {
        return mNoteDataBase.getAllNotes()
    }

    fun getNote(id: Long): NoteModel {
        return mNoteDataBase.load(id)
    }

    fun getCheckBoxesFromNote(id: Long): List<CheckBoxModel> {
        return mCheckBoxDataBase.loadAll(id)
    }

    fun deleteAll(note: NoteModel, id: Long) {
        mNoteDataBase.delete(note)
        mCheckBoxDataBase.deleteAll(id)
    }
}
