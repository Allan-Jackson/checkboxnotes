package com.allcass.checkboxnotes.service.repository

import androidx.room.*
import com.allcass.checkboxnotes.service.model.CheckBoxModel
import com.allcass.checkboxnotes.service.model.NoteModel

@Dao
interface CheckBoxDAO {

    @Insert
    fun save(checkBox: CheckBoxModel): Long

//    @Update
//    fun update(checkBox: CheckBoxModel): Int

    @Delete
    fun delete(checkBox: CheckBoxModel)

    @Query("DELETE FROM CheckBox WHERE noteId = :id")
    fun deleteAll(id: Int)


    @Query("SELECT * FROM CheckBox WHERE noteId = :id")
    fun load(id: Int): CheckBoxModel





}
