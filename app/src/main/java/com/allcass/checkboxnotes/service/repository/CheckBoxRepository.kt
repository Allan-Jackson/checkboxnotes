package com.allcass.checkboxnotes.service.repository

import android.content.Context
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.service.model.NoteModel
import java.lang.Exception

class CheckBoxRepository(context: Context) {

    // Acesso ao banco de dados
    private val mCheckBoxDataBase = CheckBoxDataBase.getDatabase(context).checkBoxDAO()
    private val mNoteDataBase = CheckBoxDataBase.getDatabase(context).noteDAO()

    fun saveNote(note: NoteModel): Long {
        return mNoteDataBase.save(note)
    }

    /*fun updateNote(note: NoteModel): Boolean {
        return mNoteDataBase.update(note)  > 0
    }*/

    fun saveCheckBox(checkboxList: List<CheckBoxModel>): Boolean {
        var inserted: Boolean = true
        checkboxList.forEach {
            if(mCheckBoxDataBase.save(it) <= 0){
                inserted = false
            }
        }
        return inserted
    }

    fun getAll(): List<NoteModel> {
        return mNoteDataBase.getAllNotes()
    }

    fun getNote(id: Int): NoteModel {
        return mNoteDataBase.load(id)
    }

    fun getCheckBoxFromNote(id: Int): CheckBoxModel {
        return mCheckBoxDataBase.load(id)
    }

    fun deleteAll(note: NoteModel, id: Int) {
        mNoteDataBase.delete(note)
        mCheckBoxDataBase.deleteAll(id)
    }
}
