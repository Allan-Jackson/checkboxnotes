
package com.allcass.checkboxnotes.service.repository

import androidx.room.Dao
import androidx.room.Insert
import com.allcass.checkboxnotes.service.model.NoteModel

@Dao
interface NoteDAO {

    @Insert
    fun save(note: NoteModel): Long

    /*@Update
    fun update(note: NoteModel): Int

    @Delete
    fun delete(note: NoteModel)*/

}

