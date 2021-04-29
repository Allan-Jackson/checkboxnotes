
package com.allcass.checkboxnotes.service.repository

import androidx.room.*
import com.allcass.checkboxnotes.service.model.NoteModel

@Dao
interface NoteDAO {

    @Insert
    fun save(note: NoteModel): Long

    /*@Update
    fun update(note: NoteModel): Int*/

    @Delete
    fun delete(note: NoteModel)

    @Query("SELECT * FROM Note WHERE id = :id")
    fun load(id: Long): NoteModel

    @Query("SELECT * FROM Note")
    fun getAllNotes(): List<NoteModel>

}

