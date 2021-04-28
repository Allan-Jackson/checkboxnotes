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
}
