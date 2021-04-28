
package com.allcass.checkboxnotes.service.repository

import android.content.Context
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.service.model.NoteModel
import java.lang.Exception

class CheckBoxRepository (context: Context){

    // Acesso ao banco de dados
    private val mCheckBoxDataBase = CheckBoxDataBase.getDatabase(context).checkBoxDAO()
    private val mNoteDataBase = CheckBoxDataBase.getDatabase(context).noteDAO()

    fun saveNote(note: NoteModel): Boolean {

    }
    fun saveCheckBox(checkBox: CheckBoxModel): Boolean{

    }

}
